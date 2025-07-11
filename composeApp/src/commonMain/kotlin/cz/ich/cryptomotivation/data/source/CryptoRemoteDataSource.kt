package cz.ich.cryptomotivation.data.source

import cz.ich.cryptomotivation.infrastructure.api.CryptoFiatResponse
import cz.ich.core.domain.model.Result

interface CryptoRemoteDataSource {

    suspend fun getCryptoList(): Result<CryptoFiatResponse>

}