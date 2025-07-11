package cz.ich.cryptomotivation.presentation.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cz.ich.cryptomotivation.domain.model.CryptoData

/**
 * Lazy column with list of cryptocurrencies.
 *
 * @param cryptoList List of [CryptoData].
 * @param showFavoriteIcon Whether to show favorite icon on list item or not.
 * @param modifier The modifier to be applied on this layout.
 * @param contentPadding The padding applied to the content of LazyColumn.
 * @param onCryptoClicked Callback for crypto item click.
 */
@Composable
fun CryptoColumn(
    cryptoList: List<CryptoData>,
    showFavoriteIcon: Boolean,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    onCryptoClicked: (CryptoData) -> Unit = {},
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding,
    ) {
        items(cryptoList) { cryptoData ->
            var imageLoadFailed by remember { mutableStateOf(false) }
            if (!imageLoadFailed) {
                CryptoCard(
                    cryptoData = cryptoData,
                    showFavoriteIcon = showFavoriteIcon,
                    onImageLoadFailed = {
                        imageLoadFailed = true
                    },
                    onClicked = {
                        onCryptoClicked(cryptoData)
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}