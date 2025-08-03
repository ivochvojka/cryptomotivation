package cz.ich.cryptomotivation.infrastructure.db

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverters

@Database(
    entities = [CryptoEntity::class],
    version = 3
)
@ConstructedBy(AppDatabaseConstructor::class)
@TypeConverters(CryptoConverters::class)
abstract class CryptoDatabase : RoomDatabase() {

    abstract fun cryptoDao(): CryptoDao

}

// The Room compiler generates the `actual` implementations.
@Suppress("KotlinNoActualForExpect", "EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<CryptoDatabase> {
    override fun initialize(): CryptoDatabase
}