package cz.ich.cryptomotivation.infrastructure.db

import androidx.room.migration.Migration
import androidx.sqlite.SQLiteConnection
import com.diamondedge.logging.logging

private val log = logging("CryptoMigration")

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(connection: SQLiteConnection) {
        log.debug { "Migration 1_2 -> CREATE TABLE" }
        connection.prepare(
            """
            CREATE TABLE CryptoEntity_2 (
                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                symbol TEXT NOT NULL,
                name TEXT NOT NULL,
                maxSupply REAL,
                iconUrl TEXT NOT NULL,
                wallet REAL NOT NULL DEFAULT 0.0
            )
        """.trimIndent()
        ).step()

        log.debug { "Migration 1_2 -> INSERT" }
        connection.prepare(
            """
            INSERT INTO CryptoEntity_2 (id, symbol, name, maxSupply, iconUrl, wallet)
            SELECT id, symbol, name, maxSupply, iconUrl, 0.0 FROM CryptoEntity
        """.trimIndent()
        ).step()

        log.debug { "Migration 1_2 -> DROP TABLE" }
        connection.prepare("DROP TABLE CryptoEntity").step()

        log.debug { "Migration 1_2 -> ALTER TABLE" }
        connection.prepare("ALTER TABLE CryptoEntity_2 RENAME TO CryptoEntity").step()
    }
}

val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(connection: SQLiteConnection) {
        log.debug { "Migration 2_3 -> ALTER TABLE" }
        connection.prepare("ALTER TABLE CryptoEntity ADD COLUMN wallet_temp TEXT").step()

        log.debug { "Migration 2_3 -> UPDATE TABLE" }
        connection.prepare("UPDATE CryptoEntity SET wallet_temp = wallet").step()

        log.debug { "Migration 2_3 -> CREATE TABLE" }
        connection.prepare(
            """
               CREATE TABLE CryptoEntity_new (
                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                symbol TEXT NOT NULL,
                name TEXT NOT NULL,
                maxSupply REAL,
                iconUrl TEXT NOT NULL,
                wallet TEXT NOT NULL
               )
            """.trimIndent()
        ).step()

        log.debug { "Migration 2_3 -> INSERT" }
        connection.prepare(
            """
            INSERT INTO CryptoEntity_new (id, symbol, name, maxSupply, iconUrl, wallet)
            SELECT id, symbol, name, maxSupply, iconUrl, wallet_temp FROM CryptoEntity
        """.trimIndent()
        ).step()

        log.debug { "Migration 2_3 -> DROP TABLE" }
        // Step 4: Drop the old table `CryptoEntity`
        connection.prepare("DROP TABLE CryptoEntity").step()

        log.debug { "Migration 2_3 -> ALTER TABLE" }
        // Step 5: Rename the new table to the old table name
        connection.prepare("ALTER TABLE CryptoEntity_new RENAME TO CryptoEntity").step()
    }
}