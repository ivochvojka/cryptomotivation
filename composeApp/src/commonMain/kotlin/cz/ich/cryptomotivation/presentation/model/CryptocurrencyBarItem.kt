package cz.ich.cryptomotivation.presentation.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DensityMedium
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Enum for NavigationBar item.
 */
enum class CryptocurrencyBarItem(
    val icon: ImageVector,
    val label: String,
) {
    All(
        icon = Icons.Filled.DensityMedium,
        label = "All",
    ),
    Favorite(
        icon = Icons.Filled.Favorite,
        label = "Favorite",
    ),
}