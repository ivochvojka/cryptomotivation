package cz.ich.cryptomotivation.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil3.compose.AsyncImage
import cryptomotivation.composeapp.generated.resources.Res
import cryptomotivation.composeapp.generated.resources.detail_max_supply
import cryptomotivation.composeapp.generated.resources.detail_not_available
import cryptomotivation.composeapp.generated.resources.loading_spinner
import cz.ich.core.domain.formatAmount
import cz.ich.cryptomotivation.domain.model.CryptoData
import cz.ich.core.presentation.component.AppProgressIndicator
import cz.ich.cryptomotivation.presentation.screenmodel.CryptoDetailScreenModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.core.parameter.parametersOf

/**
 * Screen with cryptocurrency detail.
 */
class CryptoDetailScreen(
    val data: CryptoData,
) : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    @Preview
    override fun Content() {
        val screenModel = koinScreenModel<CryptoDetailScreenModel> {
            parametersOf(data)
        }
        val viewState by screenModel.viewState.collectAsState()
        val navigator = LocalNavigator.currentOrThrow

        when {
            viewState.isLoading -> AppProgressIndicator()
            else -> CryptoDetailContent(
                data = viewState.data,
                onBackClicked = {
                    navigator.pop()
                },
                onFavoriteClicked = {
                    screenModel.updateCrypto()
                },
            )


        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun CryptoDetailContent(
        data: CryptoData,
        onBackClicked: () -> Unit,
        onFavoriteClicked: () -> Unit,
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = data.symbol,
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
                            tint = if (data.isFavorite) Color.Red else MaterialTheme.colorScheme.secondary,
                        )
                    },
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = onFavoriteClicked,
                ) {
                    Icon(
                        imageVector = if (data.isFavorite) Icons.Filled.Remove else Icons.Filled.Add,
                        contentDescription = "Floating action button."
                    )
                }
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier.padding(paddingValues).fillMaxSize()
                    .padding(horizontal = 12.dp, vertical = 24.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                AsyncImage(
                    modifier = Modifier.padding(vertical = 24.dp).size(120.dp),
                    model = data.iconUrl,
                    contentDescription = null,
                    placeholder = painterResource(Res.drawable.loading_spinner),
                    fallback = painterResource(Res.drawable.loading_spinner),
                )
                Text(
                    text = data.name,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )
                val notAvailableString = stringResource(Res.string.detail_not_available)
                Text(
                    text = stringResource(
                        Res.string.detail_max_supply,
                        data.maxSupply?.formatAmount() ?: notAvailableString
                    ),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
        }
    }
}