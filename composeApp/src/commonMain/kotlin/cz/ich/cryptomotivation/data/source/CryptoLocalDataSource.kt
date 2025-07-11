package cz.ich.cryptomotivation.data.source

import cz.ich.cryptomotivation.infrastructure.db.CryptoEntity
import kotlinx.coroutines.flow.Flow

interface CryptoLocalDataSource {

    suspend fun insertAll(cryptoList: List<CryptoEntity>)

    fun getAll(): Flow<List<CryptoEntity>>

    fun getFavorites(): Flow<List<CryptoEntity>>

    suspend fun deleteAll()

    suspend fun update(crypto: CryptoEntity)
}