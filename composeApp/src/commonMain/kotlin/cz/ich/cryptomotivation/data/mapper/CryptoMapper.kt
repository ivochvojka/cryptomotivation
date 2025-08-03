package cz.ich.cryptomotivation.data.mapper

import cz.ich.core.domain.model.BigDecimal
import cz.ich.cryptomotivation.domain.model.CryptoData
import cz.ich.cryptomotivation.infrastructure.api.CryptoInfo
import cz.ich.cryptomotivation.infrastructure.db.CryptoEntity

fun CryptoEntity.toDomain() = CryptoData(
    id = id,
    symbol = symbol,
    name = name,
    maxSupply = maxSupply,
    iconUrl = iconUrl,
    wallet = wallet,
)

fun CryptoData.toEntity() = CryptoEntity(
    id = id,
    symbol = symbol,
    name = name,
    maxSupply = maxSupply,
    iconUrl = iconUrl,
    wallet = wallet,
)

fun CryptoInfo.toDomain() = CryptoData(
    id = 0,
    symbol = symbol,
    name = name,
    maxSupply = maxSupply,
    iconUrl = iconUrl,
    wallet = BigDecimal(""),
)

fun CryptoInfo.toEntity() = CryptoEntity(
    symbol = symbol,
    name = name,
    maxSupply = maxSupply,
    iconUrl = iconUrl,
    wallet = BigDecimal(""),
)