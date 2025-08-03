package cz.ich.core.presentation.formatters

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

/**
 * Provides visual transformation for the text field input string representing decimal number
 * and sets the input cursor on the correct position.
 *
 * @property visualFormatter Visual formatter to format the input string.
 */
class DecimalVisualTransformation(
    private val visualFormatter: VisualFormatter,
) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val inputText = text.text
        val formattedNumber = visualFormatter.format(inputText)

        val newText = AnnotatedString(
            text = formattedNumber,
            spanStyles = text.spanStyles,
            paragraphStyles = text.paragraphStyles,
        )

        val offsetMapping = DecimalNumberOffsetMapping(
            originalString = inputText,
            formattedString = formattedNumber,
        )

        return TransformedText(newText, offsetMapping)
    }
}