package cz.ich.cryptomotivation.infrastructure.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CryptoFiatResponse(
    val success: Boolean,
    @SerialName("crypto")
    val cryptoInfo: Map<String, CryptoInfo>,//? = emptyMap<String, CryptoInfo>(),
    val fiat: Map<String, String>,//? = emptyMap<String, String>(),
)

@Serializable
data class CryptoInfo(
    val symbol: String,
    val name: String,
    @SerialName("name_full")
    val nameFull: String,
    @SerialName("max_supply")
    @Serializable(with = NullableDoubleOrNa::class)
    val maxSupply: Double?,
    @SerialName("icon_url")
    val iconUrl: String
)