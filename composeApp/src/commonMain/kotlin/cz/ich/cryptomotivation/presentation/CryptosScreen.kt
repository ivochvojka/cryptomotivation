package cz.ich.cryptomotivation.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cryptomotivation.composeapp.generated.resources.Res
import cryptomotivation.composeapp.generated.resources.cryptocurrencies_title
import cryptomotivation.composeapp.generated.resources.dialog_error_loading
import cz.ich.core.presentation.component.AppAlertDialog
import cz.ich.core.presentation.component.AppProgressIndicator
import cz.ich.core.presentation.component.AppTopAppBar
import cz.ich.cryptomotivation.domain.model.CryptoData
import cz.ich.cryptomotivation.presentation.component.CryptoColumn
import cz.ich.cryptomotivation.presentation.component.CryptoNavigationBar
import cz.ich.cryptomotivation.presentation.model.CryptocurrencyBarItem
import cz.ich.cryptomotivation.presentation.screenmodel.CryptosScreenModel
import org.jetbrains.compose.resources.stringResource

/**
 * Screen with all cryptocurrencies.
 */
class CryptosScreen : Screen {
    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<CryptosScreenModel>()
        val viewState by screenModel.viewState.collectAsStateWithLifecycle()

        when {
            viewState.error != null -> AppAlertDialog(
                text = stringResource(Res.string.dialog_error_loading),
                onCloseClicked = { screenModel.hideErrorDialog() },
            )

            viewState.isLoading -> AppProgressIndicator()
            else -> CryptosContent(viewState.data)
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun CryptosContent(cryptoList: List<CryptoData>) {
        val navigator = LocalNavigator.currentOrThrow
        Scaffold(
            topBar = {
                AppTopAppBar(stringResource(Res.string.cryptocurrencies_title))
            },
            bottomBar = {
                CryptoNavigationBar(
                    selectedDestination = CryptocurrencyBarItem.All,
                    onDestinationSelected = {
                        navigator.replace(FavoriteCryptosScreen())
                    },
                )
            }
        ) { paddingValues ->
            CryptoColumn(
                cryptoList = cryptoList,
                showFavoriteIcon = true,
                modifier = Modifier.padding(paddingValues),
                contentPadding = PaddingValues(12.dp),
                onCryptoClicked = { cryptoItem ->
                    navigator.push(CryptoDetailScreen(cryptoItem))
                }
            )
        }
    }
}