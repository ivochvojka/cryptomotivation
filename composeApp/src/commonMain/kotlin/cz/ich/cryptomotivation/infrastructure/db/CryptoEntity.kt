package cz.ich.cryptomotivation.infrastructure.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import cz.ich.core.domain.model.BigDecimal

@Entity
data class CryptoEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val symbol: String,
    val name: String,
    val maxSupply: Double?,
    val iconUrl: String,
    val wallet: BigDecimal,
)