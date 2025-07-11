package cz.ich.cryptomotivation.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import cryptomotivation.composeapp.generated.resources.Res
import cryptomotivation.composeapp.generated.resources.loading_spinner
import cz.ich.cryptomotivation.domain.model.CryptoData
import org.jetbrains.compose.resources.painterResource

/**
 * Card with basic cryptocurrency information.
 *
 * @param cryptoData Instance of [CryptoData].
 * @param showFavoriteIcon Whether to show favorite icon or not.
 * @param onImageLoadFailed Callback for image loading failure.
 * @param onClicked Callback for card click.
 * @param modifier The modifier to be applied on this layout.
 */
@Composable
fun CryptoCard(
    cryptoData: CryptoData,
    showFavoriteIcon: Boolean,
    onImageLoadFailed: () -> Unit,
    onClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        colors = CardDefaults.cardColors().copy(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        modifier = modifier.height(80.dp).clickable {
            onClicked()
        },
    ) {
        Row(
            modifier = Modifier.fillMaxSize().padding(10.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                modifier = Modifier.size(50.dp),
                model = cryptoData.iconUrl,
                contentDescription = null,
                placeholder = painterResource(Res.drawable.loading_spinner),
                error = painterResource(Res.drawable.loading_spinner),
                onError = {
                    onImageLoadFailed()
                }
            )
            Text(
                text = cryptoData.name,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Text(
                text = cryptoData.symbol,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.End,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            if (showFavoriteIcon && cryptoData.isFavorite) {
                Icon(
                    painter = rememberVectorPainter(image = Icons.Filled.Favorite),
                    contentDescription = null,
                    tint = Color.Red,
                )
            }
        }
    }
}