package cz.ich.cryptomotivation.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil3.compose.AsyncImage
import cryptomotivation.composeapp.generated.resources.Res
import cryptomotivation.composeapp.generated.resources.detail_max_supply
import cryptomotivation.composeapp.generated.resources.detail_not_available
import cryptomotivation.composeapp.generated.resources.loading_spinner
import cz.ich.core.presentation.component.AppProgressIndicator
import cz.ich.core.presentation.formatters.formatAmountByThousands
import cz.ich.cryptomotivation.domain.model.CryptoData
import cz.ich.cryptomotivation.presentation.component.TransactionBottomSheet
import cz.ich.cryptomotivation.presentation.model.CryptoDetailEvent
import cz.ich.cryptomotivation.presentation.model.CryptoDetailModel
import cz.ich.cryptomotivation.presentation.model.CryptoTransactionType
import cz.ich.cryptomotivation.presentation.screenmodel.CryptoDetailScreenModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.core.parameter.parametersOf

/**
 * Screen with cryptocurrency detail.
 */
class CryptoDetailScreen(
    private val data: CryptoData,
) : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    @Preview
    override fun Content() {
        val screenModel = koinScreenModel<CryptoDetailScreenModel> {
            parametersOf(data)
        }
        val viewState by screenModel.viewState.collectAsStateWithLifecycle()
        val navigator = LocalNavigator.currentOrThrow

        when {
            viewState.isLoading -> AppProgressIndicator()
            else -> CryptoDetailContent(
                model = viewState.data,
                sendEvent = screenModel::onEvent,
                onBackClicked = {
                    navigator.pop()
                },
            )


        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun CryptoDetailContent(
        model: CryptoDetailModel,
        sendEvent: (CryptoDetailEvent) -> Unit,
        onBackClicked: () -> Unit,
    ) {
        Scaffold(
            topBar = {
                DetailTopAppBar(
                    model = model,
                    onBackClicked = onBackClicked,
                )
            },
            bottomBar = {
                DetailBottomBar(
                    sendEvent = sendEvent,
                    modifier = Modifier.padding(horizontal = 12.dp),
                )
            },
        ) { paddingValues ->
            DetailContent(
                model = model,
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .padding(horizontal = 12.dp, vertical = 24.dp)
            )

            if (model.showBottomSheet) {
                TransactionBottomSheet(
                    model = model,
                    sendEvent = sendEvent
                )
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun DetailTopAppBar(
        model: CryptoDetailModel,
        modifier: Modifier = Modifier,
        onBackClicked: () -> Unit = {},
    ) {
        TopAppBar(
            title = {
                Text(
                    text = model.cryptoData.symbol,
                    modifier = Modifier.fillMaxWidth().wrapContentWidth()
                )
            },
            navigationIcon = {
                Icon(
                    painter = rememberVectorPainter(image = Icons.AutoMirrored.Filled.ArrowBack),
                    contentDescription = null,
                    modifier = Modifier.clickable(
                        onClick = onBackClicked,
                    )
                )
            },
            actions = {
                Icon(
                    painter = rememberVectorPainter(image = Icons.Filled.Favorite),
                    contentDescription = null,
                    tint = if (model.cryptoData.wallet.isPositive()) {
                        Color.Red
                    } else {
                        MaterialTheme.colorScheme.secondary
                    },
                )
            },
            modifier = modifier,
        )
    }

    @Composable
    private fun DetailContent(
        model: CryptoDetailModel,
        modifier: Modifier = Modifier,
    ) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AsyncImage(
                modifier = Modifier.padding(vertical = 24.dp).size(120.dp),
                model = model.cryptoData.iconUrl,
                contentDescription = null,
                placeholder = painterResource(Res.drawable.loading_spinner),
                fallback = painterResource(Res.drawable.loading_spinner),
            )
            Text(
                text = model.cryptoData.name,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = model.cryptoData.wallet.formatByThousands(),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
            val notAvailableString = stringResource(Res.string.detail_not_available)
            Text(
                text = stringResource(
                    Res.string.detail_max_supply,
                    model.cryptoData.maxSupply?.formatAmountByThousands() ?: notAvailableString
                ),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
    }

    @Composable
    private fun DetailBottomBar(
        sendEvent: (CryptoDetailEvent) -> Unit,
        modifier: Modifier = Modifier,
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .windowInsetsPadding(NavigationBarDefaults.windowInsets),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Button(
                onClick = {
                    sendEvent(
                        CryptoDetailEvent.UpdateTransactionBottomSheet(
                            CryptoTransactionType.Buy
                        )
                    )
                },
                modifier = Modifier.weight(1f).widthIn(min = 80.dp)
            ) {
                Text("Buy")
            }

            Button(
                onClick = {
                    sendEvent(
                        CryptoDetailEvent.UpdateTransactionBottomSheet(
                            CryptoTransactionType.Sell
                        )
                    )
                },
                modifier = Modifier.weight(1f).widthIn(min = 30.dp)
            ) {
                Text("Sell")
            }
        }
    }

}