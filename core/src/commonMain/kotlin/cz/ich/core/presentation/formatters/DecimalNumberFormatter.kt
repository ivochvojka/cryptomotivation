package cz.ich.core.presentation.formatters

const val COMMA = ','
const val DOT = '.'

/**
 * Formatter for decimal numbers.
 */
class DecimalNumberFormatter : VisualFormatter {
    override fun format(input: String): String {
        val inputParts = input.getCurrentlyUsedDecimalAmountSeparator()?.let {
            input.split(it)
        } ?: listOf(input)
        val wholeNumber = inputParts[0]

        return wholeNumber.toDoubleSafelyNullable()?.let {
            val formattedNumber = it.formatAmountByThousands()
            buildString {
                append(formattedNumber)
                if (inputParts.size > 1) {
                    append(DOT)
                    append(inputParts[1])
                }
            }
        } ?: ""
    }
}