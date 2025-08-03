package cz.ich.core.presentation.formatters

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

const val DefaultDecimalSeparator = '.'
const val GroupingSeparator = ' '

/**
 * Format Double as string separated by thousands.
 */
actual fun Double.formatAmountByThousands(): String =
    amountThousandsWithoutDecimalFormat.format(this)

private val amountThousandsWithoutDecimalFormat: DecimalFormat by lazy {
    DecimalFormat(
        "###,##0",
        amountSymbols,
    )
}

private val amountSymbols: DecimalFormatSymbols by lazy {
    DecimalFormatSymbols(Locale.getDefault()).apply {
        groupingSeparator = GroupingSeparator
        decimalSeparator = DefaultDecimalSeparator
    }
}