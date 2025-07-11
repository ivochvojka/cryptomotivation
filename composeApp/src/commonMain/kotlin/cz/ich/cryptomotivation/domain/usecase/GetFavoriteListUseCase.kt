package cz.ich.cryptomotivation.domain.usecase

import cz.ich.cryptomotivation.domain.model.CryptoData
import cz.ich.cryptomotivation.domain.repository.CryptoRepository
import cz.ich.core.domain.model.Result
import kotlinx.coroutines.flow.Flow

/**
 * Use case to get only favorite cryptocurrencies.
 */
class GetFavoriteListUseCase(private val repo: CryptoRepository) {
    operator fun invoke(): Flow<Result<List<CryptoData>>> = repo.getFavoriteList()
}