package cz.ich.cryptomotivation.database

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import cz.ich.cryptomotivation.infrastructure.db.CryptoDatabase
import kotlinx.coroutines.Dispatchers

/**
 * Android builder for database.
 */
fun getDatabaseBuilder(ctx: Context): CryptoDatabase {
    val dbFile = ctx.getDatabasePath("crypto.db")
    return Room.databaseBuilder<CryptoDatabase>(ctx, dbFile.absolutePath)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}