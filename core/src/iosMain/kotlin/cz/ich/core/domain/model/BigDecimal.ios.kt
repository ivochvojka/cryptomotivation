package cz.ich.core.domain.model

import platform.Foundation.NSDecimalNumber
import platform.Foundation.NSNumberFormatter
import platform.Foundation.NSNumberFormatterDecimalStyle

/**
 * iOS implementation of BigDecimal.
 */
@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class BigDecimal actual constructor(value: String) {

    actual constructor(value: Double?) : this(value?.toString() ?: "")

    private val value: NSDecimalNumber = NSDecimalNumber(string = value)

    actual fun add(other: BigDecimal): BigDecimal {
        return BigDecimal(value.decimalNumberByAdding(other.value).stringValue)
    }

    actual fun subtract(other: BigDecimal): BigDecimal {
        return BigDecimal(value.decimalNumberBySubtracting(other.value).stringValue)
    }

    actual fun isPositive(): Boolean {
        return value.compare(NSDecimalNumber.zero) == 1L
    }

    actual fun isNonNegative(): Boolean {
        return value.compare(NSDecimalNumber.zero).let { it == 0L || it == 1L }
    }

    actual fun formatByThousands(): String {
        val formatter = NSNumberFormatter().apply {
            numberStyle = NSNumberFormatterDecimalStyle
            minimumFractionDigits = 0u
            maximumFractionDigits = 10u
        }
        return formatter.stringFromNumber(value) ?: this.toString()
    }

    actual fun format(): String =
        if (isNaN()) {
            "0"
        } else {
            val formatter = NSNumberFormatter().apply {
                numberStyle = NSNumberFormatterDecimalStyle
                minimumFractionDigits = 0u
                maximumFractionDigits = 10u
                usesGroupingSeparator = false
                decimalSeparator = "."
            }
            formatter.stringFromNumber(value) ?: toString()
        }

    actual override fun toString(): String {
        return value.stringValue
    }

    private fun isNaN() = value.compare(NSDecimalNumber.notANumber) == 0L
}