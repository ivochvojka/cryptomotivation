package cz.ich.cryptomotivation.infrastructure.db

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers

/**
 * Android builder for database.
 */
fun getDatabaseBuilder(ctx: Context): CryptoDatabase {
    val dbFile = ctx.getDatabasePath("crypto.db")
    return Room.databaseBuilder<CryptoDatabase>(ctx, dbFile.absolutePath)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
        .build()
}