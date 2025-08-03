package cz.ich.cryptomotivation.presentation.screenmodel

import cafe.adriel.voyager.core.model.screenModelScope
import cz.ich.core.domain.model.onError
import cz.ich.core.domain.model.onSuccess
import cz.ich.core.presentation.model.UiEvent
import cz.ich.core.presentation.model.UiState
import cz.ich.core.presentation.screenmodel.BaseScreenModel
import cz.ich.cryptomotivation.domain.model.CryptoData
import cz.ich.cryptomotivation.domain.usecase.GetFavoriteListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavoriteCryptosScreenModel(
    private val getFavoriteList: GetFavoriteListUseCase,
) : BaseScreenModel<List<CryptoData>, UiEvent>() {
    private val _viewState = MutableStateFlow(
        UiState(
            data = listOf<CryptoData>(), isLoading = true
        )
    )
    override val viewState: StateFlow<UiState<List<CryptoData>>> = _viewState

    init {
        loadFavoritesList()
    }

    override fun onEvent(event: UiEvent) {
        // nothing to do
    }

    private fun loadFavoritesList() {
        log.debug { "loadFavoritesList()" }
        screenModelScope.launch {
            getFavoriteList().collect { state ->
                state.onSuccess { data ->
                    log.debug { "loadFavoritesList.onSuccess()" }
                    _viewState.update {
                        UiState(
                            data = data,
                        )
                    }
                }.onError { error ->
                    log.error { "loadFavoritesList.onError(error=$error)" }
                    UiState(
                        data = viewState.value.data,
                        error = error,
                    )
                }
            }
        }
    }
}