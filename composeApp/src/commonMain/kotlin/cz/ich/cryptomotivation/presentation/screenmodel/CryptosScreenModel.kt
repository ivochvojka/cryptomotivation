package cz.ich.cryptomotivation.presentation.screenmodel

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.diamondedge.logging.logging
import cz.ich.core.presentation.model.UiState
import cz.ich.core.domain.model.onError
import cz.ich.core.domain.model.onSuccess
import cz.ich.core.presentation.hideError
import cz.ich.cryptomotivation.domain.model.CryptoData
import cz.ich.cryptomotivation.domain.usecase.GetCryptoListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CryptosScreenModel(
    private val getCryptoList: GetCryptoListUseCase,
) : ScreenModel {
    private val _viewState = MutableStateFlow(
        UiState(
            data = listOf<CryptoData>(), isLoading = true
        )
    )
    val viewState: StateFlow<UiState<List<CryptoData>>> = _viewState

    private val log = logging(this::class.simpleName)

    init {
        loadProductList()
    }

    fun hideErrorDialog() = _viewState.hideError()

    private fun loadProductList() {
        log.debug { "loadProductList()" }
        screenModelScope.launch {
            getCryptoList().collect { state ->
                state.onSuccess { data ->
                    log.debug { "loadProductList.onSuccess()" }
                    _viewState.update {
                        UiState(
                            data = data,
                        )
                    }
                }.onError { error ->
                    log.error { "loadProductList.onError(error=$error)" }
                    _viewState.update {
                        UiState(
                            data = viewState.value.data,
                            error = error,
                        )
                    }
                }
            }
        }
    }
}