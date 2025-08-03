package cz.ich.core.domain.model

/**
 * Expect declaration for BigDecimal.
 *
 * @param value String representation of BigDecimal.
 */
@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class BigDecimal(value: String) {

    /**
     * Secondary constructor from [Double] value.
     */
    constructor(value: Double?)

    /**
     * Add another BigDecimal.
     */
    fun add(other: BigDecimal): BigDecimal

    /**
     * Subtract another BigDecimal.
     */
    fun subtract(other: BigDecimal): BigDecimal

    /**
     * Check if value is positive (bigger than zero).
     */
    fun isPositive(): Boolean

    /**
     * Check if value is non-negative (bigger than or equal to zero).
     */
    fun isNonNegative(): Boolean

    /**
     * Format value with thousands separator.
     */
    fun formatByThousands(): String

    /**
     * Format value as decimal.
     */
    fun format(): String

    /**
     * Convert to string from internal value.
     */
    override fun toString(): String
}