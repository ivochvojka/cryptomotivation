package cz.ich.cryptomotivation.presentation.model

import cz.ich.core.presentation.model.UiEvent

/**
 * Event for screen with all cryptocurrencies.
 */
sealed interface CryptosEvent : UiEvent {
    data object HideErrorDialog : CryptosEvent
}