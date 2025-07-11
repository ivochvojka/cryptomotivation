package cz.ich.cryptomotivation.domain.repository

import cz.ich.core.domain.model.Result
import cz.ich.cryptomotivation.domain.model.CryptoData
import kotlinx.coroutines.flow.Flow

interface CryptoRepository {

    /**
     * Get list of cryptocurrencies from local data source and if not exist then from remote data source.
     */
    fun getCryptoList(): Flow<Result<List<CryptoData>>>

    /**
     * Get list of favorite cryptocurrencies from local data source.
     */
    fun getFavoriteList(): Flow<Result<List<CryptoData>>>

    /**
     * Update cryptocurrency in local data source.
     */
    suspend fun updateCrypto(crypto: CryptoData)
}