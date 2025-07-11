package cz.ich.core.presentation

import cz.ich.core.presentation.model.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

/**
 * Show loading state.
 */
fun <T> MutableStateFlow<UiState<T>>.showLoading() = update {
    UiState(
        data = this.value.data,
        isLoading = true,
    )
}

/**
 * Hide error state.
 */
fun <T> MutableStateFlow<UiState<T>>.hideError() = update {
    UiState(
        data = this.value.data,
        error = null,
    )
}
