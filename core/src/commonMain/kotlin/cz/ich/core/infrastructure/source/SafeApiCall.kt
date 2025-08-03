package cz.ich.core.infrastructure.source

import cz.ich.core.domain.model.NetworkError
import cz.ich.core.domain.model.Result
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException

/**
 * Process api call and return [Result] with corresponding data or error.
 */
suspend inline fun <reified T> safeApiCall(
    apiCall: suspend () -> HttpResponse
): Result<T> {
    val response = try {
        apiCall()
    } catch (_: UnresolvedAddressException) {
        return Result.Error(NetworkError.NoInternet)
    } catch (_: Exception) {
        return Result.Error(NetworkError.Unknown)
    }

    return when (response.status.value) {
        in 200..299 -> try {
            val body = response.body<T>()
            Result.Success(body)
        } catch (e: SerializationException) {
            Result.Error(NetworkError.Serialization)
        } catch (e: Throwable) {
            Result.Error(NetworkError.Unknown)
        }

        400 -> Result.Error(NetworkError.BadRequest)
        401 -> Result.Error(NetworkError.Unauthorized)
        404 -> Result.Error(NetworkError.NotFound)
        408 -> Result.Error(NetworkError.RequestTimeout)
        409 -> Result.Error(NetworkError.Conflict)
        413 -> Result.Error(NetworkError.PayloadTooLarge)
        in 500..599 -> Result.Error(NetworkError.ServerError)
        else -> Result.Error(NetworkError.Unknown)
    }
}