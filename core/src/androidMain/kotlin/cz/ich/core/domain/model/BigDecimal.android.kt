package cz.ich.core.domain.model

import cz.ich.core.presentation.formatters.DefaultDecimalSeparator
import cz.ich.core.presentation.formatters.GroupingSeparator
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

/**
 * Android implementation of BigDecimal.
 */
@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class BigDecimal actual constructor(value: String) {

    actual constructor(value: Double?) : this(value?.toString() ?: "")

    private val value: java.math.BigDecimal? =
        value.ifEmpty { null }?.let { java.math.BigDecimal(it) }

    actual fun add(other: BigDecimal): BigDecimal {
        return BigDecimal(this.value.toValue().add(other.value.toValue()).toString())
    }

    actual fun subtract(other: BigDecimal): BigDecimal {
        return BigDecimal(value.toValue().subtract(other.value.toValue()).toString())
    }

    actual fun isPositive(): Boolean =
        (value ?: java.math.BigDecimal(-1)).compareTo(java.math.BigDecimal.ZERO) == 1

    actual fun isNonNegative(): Boolean =
        (value ?: java.math.BigDecimal(-1)).compareTo(java.math.BigDecimal.ZERO) != -1

    actual fun formatByThousands(): String = value?.let {
        amountThousandsWithDecimalFormat.format(it)
    } ?: "0"

    actual fun format(): String = value?.let {
        amountWithDecimalFormat.format(it)
    } ?: ""

    actual override fun toString(): String {
        return value.toString()
    }

    private val amountThousandsWithDecimalFormat: DecimalFormat by lazy {
        DecimalFormat("#,###.####################").apply {
            decimalFormatSymbols = amountSymbols
        }
    }

    private val amountWithDecimalFormat: DecimalFormat by lazy {
        DecimalFormat("#.####################")
    }

    private val amountSymbols: DecimalFormatSymbols by lazy {
        DecimalFormatSymbols(Locale.getDefault()).apply {
            groupingSeparator = GroupingSeparator
            decimalSeparator = DefaultDecimalSeparator
        }
    }

    private fun java.math.BigDecimal?.toValue(): java.math.BigDecimal =
        this ?: java.math.BigDecimal(0.0)

}