package cz.ich.cryptomotivation.domain.usecase

import cz.ich.cryptomotivation.domain.model.CryptoData
import cz.ich.cryptomotivation.domain.repository.CryptoRepository

/**
 * Use case to update cryptocurrency.
 */
class UpdateCryptoUseCase(private val repo: CryptoRepository) {
    suspend operator fun invoke(crypto: CryptoData) = repo.updateCrypto(crypto)
}