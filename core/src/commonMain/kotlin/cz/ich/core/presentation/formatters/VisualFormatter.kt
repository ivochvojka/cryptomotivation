package cz.ich.core.presentation.formatters

/**
 * Interface for visual formatting of input strings.
 */
interface VisualFormatter {
    fun format(input: String): String
}