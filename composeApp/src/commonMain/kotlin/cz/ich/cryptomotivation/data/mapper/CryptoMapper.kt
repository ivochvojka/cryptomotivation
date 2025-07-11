package cz.ich.cryptomotivation.data.mapper

import cz.ich.cryptomotivation.domain.model.CryptoData
import cz.ich.cryptomotivation.infrastructure.api.CryptoInfo
import cz.ich.cryptomotivation.infrastructure.db.CryptoEntity

fun CryptoEntity.toDomain() = CryptoData(
    id = id,
    symbol = symbol,
    name = name,
    maxSupply = maxSupply,
    iconUrl = iconUrl,
    isFavorite = isFavorite,
)

fun CryptoData.toEntity() = CryptoEntity(
    id = id,
    symbol = symbol,
    name = name,
    maxSupply = maxSupply,
    iconUrl = iconUrl,
    isFavorite = isFavorite,
)

fun CryptoInfo.toDomain() = CryptoData(
    id = 0,
    symbol = symbol,
    name = name,
    maxSupply = maxSupply,
    iconUrl = iconUrl
)

fun CryptoInfo.toEntity() = CryptoEntity(
    symbol = symbol,
    name = name,
    maxSupply = maxSupply,
    iconUrl = iconUrl
)