package cz.ich.core.presentation.formatters

const val GROUPING_SEPARATOR = ' '

/**
 * Get decimal separator from string.
 */
fun String.getCurrentlyUsedDecimalAmountSeparator() = when {
    contains(DOT) -> DOT
    contains(COMMA) -> COMMA
    else -> null
}

/**
 * Sanitize decimal number to unique format.
 */
fun String.sanitizeDecimalNumber(): String {
    return replace(
        COMMA,
        DOT
    )
        .removeWhiteSpaces()
        .removeGroupingSeparators()
}

fun String.removeWhiteSpaces(): String = this.filterNot { it.isWhitespace() }

fun String.removeGroupingSeparators(): String {
    return this.replace(GROUPING_SEPARATOR.toString(), "")
}

fun String.toDoubleSafelyNullable(): Double? = sanitizeDecimalNumber().toDoubleOrNull()