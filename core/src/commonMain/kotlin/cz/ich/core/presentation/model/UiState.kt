package cz.ich.core.presentation.model

import cz.ich.core.domain.model.BaseError

/**
 * Base class for UI state.
 *
 * @property data The data associated with the UI state. Screens use data to display corresponding content.
 * @property error Allow display information when some error occurred.
 * @property isLoading Determine loading state.
 */
open class UiState<T>(
    val data: T,
    val error: BaseError? = null,
    val isLoading: Boolean = false,
)