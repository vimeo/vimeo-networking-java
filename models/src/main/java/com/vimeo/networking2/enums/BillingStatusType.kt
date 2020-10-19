package com.vimeo.networking2.enums

/**
 * The status of this user's billing information.
 */
enum class BillingStatusType(override val value: String?) : StringValue {

    /**
     * The user's billing information is active.
     */
    ACTIVE("active"),

    /**
     * The user's billing information has been canceled.
     */
    CANCELED("canceled"),

    /**
     * The user's billing information has expired.
     */
    EXPIRED("expired"),

    /**
     * The user's billing information is currently on hold.
     */
    ON_HOLD("on_hold"),

    /**
     * Unknown billing status.
     */
    UNKNOWN(null)
}
