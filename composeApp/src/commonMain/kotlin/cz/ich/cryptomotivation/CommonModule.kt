package cz.ich.cryptomotivation

import cz.ich.cryptomotivation.data.source.CryptoLocalDataSource
import cz.ich.cryptomotivation.data.source.CryptoRemoteDataSource
import cz.ich.cryptomotivation.data.repository.DefaultCryptoRepository
import cz.ich.cryptomotivation.domain.repository.CryptoRepository
import cz.ich.cryptomotivation.domain.usecase.GetCryptoListUseCase
import cz.ich.cryptomotivation.domain.usecase.GetFavoriteListUseCase
import cz.ich.cryptomotivation.domain.usecase.UpdateCryptoUseCase
import cz.ich.cryptomotivation.infrastructure.source.KtorCryptoRemoteDataSource
import cz.ich.cryptomotivation.infrastructure.source.RoomCryptoLocalDataSource
import cz.ich.cryptomotivation.presentation.screenmodel.CryptoDetailScreenModel
import cz.ich.cryptomotivation.presentation.screenmodel.CryptosScreenModel
import cz.ich.cryptomotivation.presentation.screenmodel.FavoriteCryptosScreenModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val commonModule = module {
    singleOf(::KtorCryptoRemoteDataSource) bind CryptoRemoteDataSource::class
    singleOf(::RoomCryptoLocalDataSource) bind CryptoLocalDataSource::class
    singleOf(::DefaultCryptoRepository) bind CryptoRepository::class
    factoryOf(::GetCryptoListUseCase)
    factoryOf(::GetFavoriteListUseCase)
    factoryOf(::UpdateCryptoUseCase)
    factoryOf(::CryptosScreenModel)
    factoryOf(::FavoriteCryptosScreenModel)
    factoryOf(::CryptoDetailScreenModel)
}