package cz.ich.cryptomotivation.infrastructure.db

import androidx.room.TypeConverter
import cz.ich.core.domain.model.BigDecimal

/**
 * Converters for Room DB.
 */
class CryptoConverters {

    @TypeConverter
    fun fromBigDecimal(value: BigDecimal): String {
        return value.format()
    }

    @TypeConverter
    fun toBigDecimal(value: String): BigDecimal {
        return BigDecimal(value)
    }
}