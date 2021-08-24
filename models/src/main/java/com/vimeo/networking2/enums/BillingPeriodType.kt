package com.vimeo.networking2.enums

/**
 * Billing periods for PRO, Plus, PRO Unlimited plans.
 */
enum class BillingPeriodType(override val value: String?) : StringValue {

    /**
     * User will be charged monthly.
     */
    MONTHLY("month"),

    /**
     * User will be charged yearly.
     */
    YEARLY("year"),

    /**
     * Unknown billing period.
     */
    UNKNOWN(null)
}
