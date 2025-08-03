package cz.ich.cryptomotivation.domain.model

import cz.ich.core.domain.model.BigDecimal

/**
 * Data class representing cryptocurrency.
 *
 * @property id The unique identifier of the cryptocurrency.
 * @property symbol The symbol representing abbreviation of the cryptocurrency.
 * @property name Full name of the cryptocurrency.
 * @property maxSupply Maximum supply of the cryptocurrency.
 * @property iconUrl The URL of the cryptocurrency icon.
 * @property wallet The amount of the cryptocurrency in the wallet.
 */
data class CryptoData(
    val id: Int,
    val symbol: String,
    val name: String,
    val maxSupply: Double?,
    val iconUrl: String,
    val wallet: BigDecimal,
)