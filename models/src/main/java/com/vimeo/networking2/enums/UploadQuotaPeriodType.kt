package com.vimeo.networking2.enums

/**
 * The renewal frequency of the upload quota.
 */
enum class UploadQuotaPeriodType(override val value: String?) : StringValue {
    /**
     * The quota renews weekly.
     */
    WEEKLY("weekly"),

    /**
     * The quota renews monthly.
     */
    MONTHLY("monthly"),

    /**
     * The quota renews yearly.
     */
    YEARLY("yearly"),

    /**
     * The user doesn't have a periodic quota.
     */
    LIFETIME("lifetime"),

    /**
     * The quota renews on an unknown schedule.
     */
    UNKNOWN(null)
}
