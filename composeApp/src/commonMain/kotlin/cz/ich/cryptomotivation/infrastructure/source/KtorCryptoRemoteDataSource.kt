package cz.ich.cryptomotivation.infrastructure.source

import cz.ich.core.domain.model.Result
import cz.ich.core.infrastructure.source.safeApiCall
import cz.ich.cryptomotivation.BuildKonfig
import cz.ich.cryptomotivation.data.source.CryptoRemoteDataSource
import cz.ich.cryptomotivation.infrastructure.api.CryptoFiatResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class KtorCryptoRemoteDataSource(private val httpClient: HttpClient) : CryptoRemoteDataSource {
    override suspend fun getCryptoList(): Result<CryptoFiatResponse> = safeApiCall {
        httpClient.get(
            urlString = "https://api.coinlayer.com/list"
        ) {
            parameter("access_key", BuildKonfig.COINLAYER_API_KEY)
        }
    }
}