package cz.ich.cryptomotivation.presentation.component

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cz.ich.cryptomotivation.presentation.model.CryptocurrencyBarItem

/**
 * Navigation bar for list of all and favorite cryptocurrencies.
 *
 * @param selectedDestination Selected navigation item.
 * @param modifier The modifier to be applied on this layout.
 * @param onDestinationSelected Callback for navigation item click.
 */
@Composable
fun CryptoNavigationBar(
    selectedDestination: CryptocurrencyBarItem,
    modifier: Modifier = Modifier,
    onDestinationSelected: (CryptocurrencyBarItem) -> Unit = {},
) {
    NavigationBar(
        modifier = modifier,
    ) {
        CryptocurrencyBarItem.entries.forEach { destination ->
            val isSelected = selectedDestination == destination
            NavigationBarItem(
                selected = isSelected,
                onClick = { onDestinationSelected(destination) },
                icon = {
                    Icon(
                        imageVector = destination.icon,
                        contentDescription = null,
                        tint = if (isSelected) Color.Red else MaterialTheme.colorScheme.secondary
                    )
                },
                label = { Text(destination.label, style = MaterialTheme.typography.bodySmall) },
                colors = NavigationBarItemDefaults.colors().copy(
                    selectedTextColor = MaterialTheme.colorScheme.surfaceVariant,
                    unselectedTextColor = MaterialTheme.colorScheme.secondary,
                ),
            )
        }
    }
}