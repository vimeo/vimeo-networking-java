package com.vimeo.networking2.enums

/**
 * The status of this user's billing information.
 */
enum class BillingStatusType(override val value: String?) : StringValue {

    /**
     * The user's billing information is active and will auto-renew.
     */
    ACTIVE("active"),

    /**
     * The user's billing information has been canceled, but the subscription could still be active depending
     * on if the expiration date has passed.
     */
    CANCELED("canceled"),

    /**
     * The user's billing information is in a pending state and is likely to change.
     */
    PENDING("pending"),

    /**
     * The user's billing information is in a grace period before it's put on hold while the auto-renew
     * charge is retried.
     */
    GRACE_PERIOD("grace_period"),

    /**
     * The user's billing information is currently on hold due to a payment failure and the grace period expiring.
     * (Google Play only)
     */
    ON_HOLD("on_hold"),

    /**
     * Unknown billing status.
     */
    UNKNOWN(null)
}
