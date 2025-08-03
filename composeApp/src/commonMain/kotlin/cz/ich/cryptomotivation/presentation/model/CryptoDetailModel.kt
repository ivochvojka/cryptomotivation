package cz.ich.cryptomotivation.presentation.model

import cz.ich.cryptomotivation.domain.model.CryptoData

/**
 * Model for crypto detail screen.
 *
 * @param cryptoData Instance of [CryptoData].
 * @param transactionType Type of transaction with cryptocurrency.
 * @param currentAmount Current amount of cryptocurrency entered by user.
 * @param showBottomSheet Whether to show bottom sheet or not.
 */
data class CryptoDetailModel(
    val cryptoData: CryptoData,
    val transactionType: CryptoTransactionType,
    val currentAmount: String = "",
    val showBottomSheet: Boolean = false,
)
