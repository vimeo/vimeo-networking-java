package com.vimeo.networking2.enums

/**
 * The renewal frequency of the upload quota.
 */
enum class UploadQuotaPeriodType(override val value: String?) : StringValue {
    /**
     * The quota renews weekly.
     */
    WEEKLY("week"),

    /**
     * The quota renews monthly.
     */
    MONTHLY("month"),

    /**
     * The quota renews yearly.
     */
    YEARLY("year"),

    /**
     * The user doesn't have a periodic quota.
     */
    LIFETIME("lifetime"),

    /**
     * The quota renews on an unknown schedule.
     */
    UNKNOWN(null)
}
