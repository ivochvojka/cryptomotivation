package cz.ich.cryptomotivation.presentation.model

import cz.ich.core.presentation.model.UiEvent

/**
 * Event for crypto detail screen.
 */
sealed interface CryptoDetailEvent : UiEvent {
    data class UpdateTransactionBottomSheet(val type: CryptoTransactionType) : CryptoDetailEvent

    data class OnAmountChanged(val amount: String) : CryptoDetailEvent

    data object SellAll : CryptoDetailEvent

    data object ProcessTransaction : CryptoDetailEvent
}