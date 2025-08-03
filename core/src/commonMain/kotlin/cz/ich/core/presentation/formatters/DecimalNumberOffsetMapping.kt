package cz.ich.core.presentation.formatters

import androidx.compose.ui.text.input.OffsetMapping

/**
 * [OffsetMapping] for large decimal numbers.
 */
class DecimalNumberOffsetMapping(
    private val originalString: String,
    private val formattedString: String,
) : OffsetMapping {

    override fun originalToTransformed(offset: Int): Int {
        if (formattedString.isEmpty()) {
            return 0
        }

        val separator = originalString.getCurrentlyUsedDecimalAmountSeparator()
        val separatorPosition = separator?.let {
            originalString.indexOf(it) - 1
        } ?: (originalString.length - 1)

        val newOffset = if (separatorPosition == -1 || offset <= separatorPosition) {
            offset + calculateSpacesCount(offset)
        } else {
            offset + separatorPosition / 3
        }
        return newOffset
    }

    override fun transformedToOriginal(offset: Int): Int {
        if (formattedString.isEmpty()) {
            return 0
        }

        val spacesCount = formattedString
            .substring(0, offset)
            .count { it == ' ' }

        val newOffset = offset - spacesCount
        return newOffset
    }

    private fun calculateSpacesCount(offset: Int): Int {
        var count = 0
        for (i in 0 until offset) {
            if (originalString[i] != formattedString[i + count]) {
                count++
            }
        }
        return count
    }
}
