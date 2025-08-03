package cz.ich.cryptomotivation.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import cz.ich.core.domain.model.BigDecimal
import cz.ich.core.presentation.formatters.DecimalNumberFormatter
import cz.ich.core.presentation.formatters.DecimalVisualTransformation
import cz.ich.cryptomotivation.presentation.model.CryptoDetailEvent
import cz.ich.cryptomotivation.presentation.model.CryptoDetailModel
import cz.ich.cryptomotivation.presentation.model.CryptoTransactionType

/**
 * Bottom sheet for cryptocurrency transaction.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionBottomSheet(
    model: CryptoDetailModel,
    modifier: Modifier = Modifier.Companion,
    sendEvent: (CryptoDetailEvent) -> Unit = {},
) {
    ModalBottomSheet(
        onDismissRequest = {
            sendEvent(
                CryptoDetailEvent.UpdateTransactionBottomSheet(
                    CryptoTransactionType.None
                )
            )
        },
        sheetState = rememberModalBottomSheetState(),
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier.Companion.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.Companion.CenterHorizontally,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                OutlinedTextField(
                    value = model.currentAmount,
                    onValueChange = { value ->
                        if (value.length < MaxAmountLength) {
                            sendEvent(
                                CryptoDetailEvent.OnAmountChanged(
                                    amount = value
                                )
                            )
                        }
                    },
                    label = {
                        Text("Amount")
                    },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Companion.Decimal),
                    visualTransformation = remember {
                        DecimalVisualTransformation(DecimalNumberFormatter())
                    },
                    modifier = Modifier.weight(1f),
                )
                if (model.transactionType == CryptoTransactionType.Sell) {
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = { sendEvent(CryptoDetailEvent.SellAll) },
                    ) {
                        Text(
                            text = "All"
                        )
                    }
                }
            }

            Button(
                onClick = { sendEvent(CryptoDetailEvent.ProcessTransaction) },
                enabled = when (model.transactionType) {
                    CryptoTransactionType.Buy -> isBuyEnabled(model)
                    CryptoTransactionType.Sell -> isSellEnabled(model)
                    else -> false
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = when (model.transactionType) {
                        CryptoTransactionType.Buy -> "Buy"
                        CryptoTransactionType.Sell -> "Sell"
                        else -> ""
                    }
                )
            }
        }
    }
}

private fun isBuyEnabled(model: CryptoDetailModel): Boolean = with(model) {
    if (currentAmount.isEmpty()) {
        false
    } else {
        cryptoData.maxSupply?.let { maxSupply ->
            BigDecimal(maxSupply)
                .subtract(cryptoData.wallet)
                .subtract(BigDecimal(currentAmount.toDoubleOrNull()))
                .isNonNegative()
        } ?: false
    }
}

private fun isSellEnabled(model: CryptoDetailModel): Boolean = with(model) {
    if (currentAmount.isEmpty()) {
        false
    } else {
        cryptoData.wallet
            .subtract(BigDecimal(currentAmount.toDoubleOrNull()))
            .isNonNegative()
    }
}

private const val MaxAmountLength = 20