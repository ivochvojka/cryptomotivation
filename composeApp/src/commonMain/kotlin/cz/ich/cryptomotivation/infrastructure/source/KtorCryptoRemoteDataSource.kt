package cz.ich.cryptomotivation.infrastructure.source

import cz.ich.core.domain.model.NetworkError
import cz.ich.core.domain.model.Result
import cz.ich.cryptomotivation.BuildKonfig
import cz.ich.cryptomotivation.data.source.CryptoRemoteDataSource
import cz.ich.cryptomotivation.infrastructure.api.CryptoFiatResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException

class KtorCryptoRemoteDataSource(private val httpClient: HttpClient) : CryptoRemoteDataSource {
    override suspend fun getCryptoList(): Result<CryptoFiatResponse> {
        val response = try {
            httpClient.get(
                urlString = "https://api.coinlayer.com/list"
            ) {
                parameter("access_key", BuildKonfig.COINLAYER_API_KEY)
            }
        } catch (e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NoInternet)
        } catch (e: Exception) {
            return Result.Error(NetworkError.Unknown)
        }

        return when (response.status.value) {
            in 200..299 -> try {
                val censoredText = response.body<CryptoFiatResponse>()
                Result.Success(censoredText)
            } catch (e: SerializationException) {
                Result.Error(NetworkError.Serialization)
            } catch (e: Throwable) {
                Result.Error(NetworkError.Unknown)
            }

            401 -> Result.Error(NetworkError.Unauthorized)
            409 -> Result.Error(NetworkError.Conflict)
            408 -> Result.Error(NetworkError.RequestTimeout)
            413 -> Result.Error(NetworkError.PayloadTooLarge)
            in 500..599 -> Result.Error(NetworkError.ServerError)
            else -> Result.Error(NetworkError.Unknown)
        }
    }
}