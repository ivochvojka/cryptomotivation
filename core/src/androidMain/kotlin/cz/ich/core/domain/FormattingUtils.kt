package cz.ich.core.domain

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

actual fun Double.formatAmount(): String = amountWithoutDecimalFormat.format(this)

private val amountWithoutDecimalFormat: DecimalFormat by lazy {
    DecimalFormat(
        "###,##0",
        amountSymbols,
    )
}

private val amountSymbols: DecimalFormatSymbols by lazy {
    DecimalFormatSymbols(Locale.getDefault()).apply {
        groupingSeparator = GROUPING_SEPARATOR
        decimalSeparator = DEFAULT_DECIMAL_SEPARATOR
    }
}

private const val DEFAULT_DECIMAL_SEPARATOR = ','
private const val GROUPING_SEPARATOR = ' '