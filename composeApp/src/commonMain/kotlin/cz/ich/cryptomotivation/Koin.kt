package cz.ich.cryptomotivation

import cz.ich.core.networkingModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            commonModule,
            networkingModule,
            platformModule()
        )
    }