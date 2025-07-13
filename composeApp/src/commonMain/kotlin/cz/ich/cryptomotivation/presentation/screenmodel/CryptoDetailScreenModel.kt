package cz.ich.cryptomotivation.presentation.screenmodel

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.diamondedge.logging.logging
import cz.ich.core.presentation.model.UiState
import cz.ich.core.presentation.showLoading
import cz.ich.cryptomotivation.domain.model.CryptoData
import cz.ich.cryptomotivation.domain.usecase.UpdateCryptoUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CryptoDetailScreenModel(
    cryptoData: CryptoData,
    private val updateCrypto: UpdateCryptoUseCase,
) : ScreenModel {
    private val _viewState = MutableStateFlow(UiState(data = cryptoData))
    val viewState: StateFlow<UiState<CryptoData>> = _viewState

    private val log = logging(this::class.simpleName)

    fun updateCrypto() {
        log.debug { "updateCrypto()" }
        _viewState.showLoading()
        screenModelScope.launch {
            val data = viewState.value.data.copy(
                isFavorite = !viewState.value.data.isFavorite
            )
            updateCrypto(data)
            _viewState.update {
                UiState(
                    data = data,
                )
            }
        }

    }
}