package cz.ich.cryptomotivation.domain.usecase

import cz.ich.core.domain.model.Result
import cz.ich.cryptomotivation.domain.model.CryptoData
import cz.ich.cryptomotivation.domain.repository.CryptoRepository
import kotlinx.coroutines.flow.Flow

/**
 * Use case to get all cryptocurrencies.
 */
class GetCryptoListUseCase(private val repo: CryptoRepository) {
    operator fun invoke(): Flow<Result<List<CryptoData>>> = repo.getCryptoList()
}