package cz.ich.cryptomotivation.presentation.screenmodel

import cafe.adriel.voyager.core.model.screenModelScope
import cz.ich.core.domain.model.BigDecimal
import cz.ich.core.presentation.model.UiState
import cz.ich.core.presentation.screenmodel.BaseScreenModel
import cz.ich.core.presentation.showLoading
import cz.ich.cryptomotivation.domain.model.CryptoData
import cz.ich.cryptomotivation.domain.usecase.UpdateCryptoUseCase
import cz.ich.cryptomotivation.presentation.model.CryptoDetailEvent
import cz.ich.cryptomotivation.presentation.model.CryptoDetailModel
import cz.ich.cryptomotivation.presentation.model.CryptoTransactionType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CryptoDetailScreenModel(
    cryptoData: CryptoData,
    private val updateCrypto: UpdateCryptoUseCase,
) : BaseScreenModel<CryptoDetailModel, CryptoDetailEvent>() {

    private val _viewState = MutableStateFlow(
        UiState(
            data = CryptoDetailModel(
                cryptoData = cryptoData,
                transactionType = CryptoTransactionType.None,
            )
        )
    )
    override val viewState: StateFlow<UiState<CryptoDetailModel>> = _viewState.asStateFlow()

    init {
        log.debug { "init(cryptoData=$cryptoData)" }
    }

    override fun onEvent(event: CryptoDetailEvent) {
        log.debug { "onEvent(event=$event)" }
        when (event) {
            is CryptoDetailEvent.UpdateTransactionBottomSheet -> updateTransactionBottomSheet(event)
            is CryptoDetailEvent.OnAmountChanged -> onAmountChanged(event)
            is CryptoDetailEvent.ProcessTransaction -> processTransaction()
            CryptoDetailEvent.SellAll -> sellAll()
        }
    }

    private fun onAmountChanged(event: CryptoDetailEvent.OnAmountChanged) {
        val newAmount = event.amount
            .replace(" ", "") // replace empty spaces
            .replace("^0+(?!$|\\.)".toRegex(), "") // replace zeroes
            .replace("^\\.(\\d+)".toRegex(), "0.$1") // add zero before decimal point

        _viewState.update {
            UiState(
                data = viewState.value.data.copy(
                    currentAmount = newAmount.toDoubleOrNull()?.let { newAmount } ?: ""
                ),
            )
        }
    }

    private fun sellAll() {
        _viewState.update {
            UiState(
                data = viewState.value.data.copy(
                    currentAmount = viewState.value.data.cryptoData.wallet.format()
                ),
            )
        }
    }


    private fun updateTransactionBottomSheet(event: CryptoDetailEvent.UpdateTransactionBottomSheet) {
        when (event.type) {
            CryptoTransactionType.None -> closeTransactionBottomSheet()
            else -> openTransactionBottomSheet(event.type)
        }
    }

    private fun processTransaction() {
        _viewState.showLoading()
        screenModelScope.launch {
            with(viewState.value.data) {
                val amount = currentAmount.toDoubleOrNull()
                val updatedCryptoData = cryptoData.copy(
                    wallet = when (transactionType) {
                        CryptoTransactionType.Buy -> cryptoData.wallet.add(BigDecimal(currentAmount))
                        else -> cryptoData.wallet.subtract(BigDecimal(currentAmount))
                    }
                )

                updateCrypto(updatedCryptoData)
                _viewState.update {
                    UiState(
                        data = viewState.value.data.copy(
                            cryptoData = updatedCryptoData,
                            currentAmount = "",
                        ),
                    )
                }
                closeTransactionBottomSheet()
            }
        }
    }

    private fun openTransactionBottomSheet(type: CryptoTransactionType) {
        _viewState.update {
            UiState(
                data = viewState.value.data.copy(
                    transactionType = type,
                    showBottomSheet = true
                ),
            )
        }
    }

    private fun closeTransactionBottomSheet() {
        _viewState.update {
            UiState(
                data = viewState.value.data.copy(
                    transactionType = CryptoTransactionType.None,
                    showBottomSheet = false,
                    currentAmount = "",
                ),
            )
        }
    }
}