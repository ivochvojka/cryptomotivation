package cz.ich.cryptomotivation.infrastructure.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CryptoDao {

    @Insert
    suspend fun insertAll(cryptoList: List<CryptoEntity>)

    @Query("DELETE FROM CryptoEntity")
    suspend fun deleteAll()

    @Query("SELECT * FROM CryptoEntity")
    fun getAllCrypto(): Flow<List<CryptoEntity>>

    @Query("SELECT * FROM CryptoEntity WHERE wallet != 0.0 AND wallet != 0 AND wallet IS NOT NULL AND wallet != ''")
    fun getFavoriteCryptos(): Flow<List<CryptoEntity>>

    @Update
    suspend fun update(crypto: CryptoEntity)
}