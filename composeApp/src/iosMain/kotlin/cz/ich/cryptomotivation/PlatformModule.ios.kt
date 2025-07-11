package cz.ich.cryptomotivation

import cz.ich.cryptomotivation.database.getDatabaseBuilder
import cz.ich.cryptomotivation.infrastructure.db.CryptoDatabase
import org.koin.dsl.module

actual fun platformModule() = module {
    single<CryptoDatabase> { getDatabaseBuilder() }
}