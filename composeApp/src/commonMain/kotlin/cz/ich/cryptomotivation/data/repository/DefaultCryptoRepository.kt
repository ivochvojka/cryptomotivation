package cz.ich.cryptomotivation.data.repository

import com.diamondedge.logging.logging
import cz.ich.cryptomotivation.domain.model.CryptoData
import cz.ich.core.domain.model.Result
import cz.ich.core.domain.model.map
import cz.ich.cryptomotivation.data.source.CryptoLocalDataSource
import cz.ich.cryptomotivation.data.source.CryptoRemoteDataSource
import cz.ich.cryptomotivation.data.mapper.toDomain
import cz.ich.cryptomotivation.data.mapper.toEntity
import cz.ich.cryptomotivation.domain.repository.CryptoRepository
import cz.ich.cryptomotivation.infrastructure.db.CryptoEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultCryptoRepository(
    private val localDataSource: CryptoLocalDataSource,
    private val remoteDataSource: CryptoRemoteDataSource,
) : CryptoRepository {

    val log = logging(DefaultCryptoRepository::class.simpleName)

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getCryptoList(): Flow<Result<List<CryptoData>>> {
        return localDataSource.getAll()
            .map { cryptoEntities ->
                if (cryptoEntities.isEmpty()) {
                    log.debug { "Refresh from remote data" }
                    when (val remoteData = remoteDataSource.getCryptoList()) {
                        is Result.Success -> {
                            val cryptoInfo = remoteData.data.cryptoInfo
                            localDataSource.deleteAll()
                            localDataSource.insertAll(
                                cryptoInfo.map { it.value.toEntity() }
                            )
                            Result.Success(emptyList<CryptoEntity>())
                        }

                        is Result.Error -> remoteData
                    }
                } else {
                    Result.Success(cryptoEntities)
                }
            }.map { result ->
                result.map { cryptoEntities -> cryptoEntities.map { entity -> entity.toDomain() } }
            }
    }

    override fun getFavoriteList(): Flow<Result<List<CryptoData>>> =
        localDataSource.getFavorites().map { entityList ->
            Result.Success(entityList.map { it.toDomain() })
        }

    override suspend fun updateCrypto(crypto: CryptoData) {
        localDataSource.update(crypto.toEntity())
    }

}