package cz.ich.cryptomotivation.infrastructure.source

import com.diamondedge.logging.logging
import cz.ich.cryptomotivation.data.repository.DefaultCryptoRepository
import cz.ich.cryptomotivation.data.source.CryptoLocalDataSource
import cz.ich.cryptomotivation.infrastructure.db.CryptoDatabase
import cz.ich.cryptomotivation.infrastructure.db.CryptoEntity
import kotlinx.coroutines.flow.Flow

class RoomCryptoLocalDataSource(
    private val cryptoDatabase: CryptoDatabase
) : CryptoLocalDataSource {

    val log = logging(RoomCryptoLocalDataSource::class.simpleName)

    override suspend fun insertAll(cryptoList: List<CryptoEntity>) =
        cryptoDatabase.cryptoDao().insertAll(cryptoList)

    override fun getAll(): Flow<List<CryptoEntity>> = cryptoDatabase.cryptoDao().getAllCrypto()

    override fun getFavorites(): Flow<List<CryptoEntity>> =
        cryptoDatabase.cryptoDao().getFavoriteCryptos()

    override suspend fun deleteAll() = cryptoDatabase.cryptoDao().deleteAll()

    override suspend fun update(crypto: CryptoEntity) {
        log.debug { "update(crypto=$crypto)" }
        cryptoDatabase.cryptoDao().update(crypto)
    }

}