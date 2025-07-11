package cz.ich.cryptomotivation.domain.model

/**
 * Data class representing cryptocurrency.
 *
 * @property id The unique identifier of the cryptocurrency.
 * @property symbol The symbol representing abbreviation of the cryptocurrency.
 * @property name Full name of the cryptocurrency.
 * @property maxSupply Maximum supply of the cryptocurrency.
 * @property iconUrl The URL of the cryptocurrency icon.
 * @property isFavorite Whether the cryptocurrency is a favorite or not.
 * Favorite cryptocurrencies are displayed in own navigation bar item.
 */
data class CryptoData(
    val id: Int,
    val symbol: String,
    val name: String,
    val maxSupply: Double?,
    val iconUrl: String,
    val isFavorite: Boolean = false,
)