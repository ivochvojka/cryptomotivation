package cz.ich.cryptomotivation.database

import androidx.room.Room
import cz.ich.cryptomotivation.infrastructure.db.CryptoDatabase
import cz.ich.cryptomotivation.infrastructure.db.MIGRATION_1_2
import cz.ich.cryptomotivation.infrastructure.db.MIGRATION_2_3
import platform.Foundation.NSHomeDirectory

/**
 * iOS builder for database.
 */
fun getDatabaseBuilder(): CryptoDatabase {
    val dbFile = "${NSHomeDirectory()}/crypto.db"
    return Room.databaseBuilder<CryptoDatabase>(
        name = dbFile,
    ).setDriver(_root_ide_package_.androidx.sqlite.driver.bundled.BundledSQLiteDriver())
        .fallbackToDestructiveMigrationOnDowngrade(true)
        .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
        .build()
}