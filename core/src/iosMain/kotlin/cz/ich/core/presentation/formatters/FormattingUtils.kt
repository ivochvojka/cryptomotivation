package cz.ich.core.presentation.formatters

import platform.Foundation.NSNumber
import platform.Foundation.NSNumberFormatter
import platform.Foundation.NSNumberFormatterDecimalStyle
import platform.Foundation.numberWithDouble

actual fun Double.formatAmountByThousands(): String {
    val formatter = NSNumberFormatter().apply {
        numberStyle = NSNumberFormatterDecimalStyle
        minimumFractionDigits = 0u
        maximumFractionDigits = 0u
    }
    return formatter.stringFromNumber(NSNumber.numberWithDouble(this)) ?: this.toString()
}