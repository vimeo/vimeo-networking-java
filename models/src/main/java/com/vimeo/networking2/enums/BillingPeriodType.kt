package com.vimeo.networking2.enums

import com.squareup.moshi.Json

/**
 * Billing periods for PRO, Plus, PRO Unlimited plans.
 */
enum class BillingPeriodType {

    /**
     * User will be charged monthly.
     */
    @Json(name = "monthly")
    MONTHLY,

    /**
     * User will be charged yearly.
     */
    @Json(name = "yearly")
    YEARLY,

    /**
     * Unknown billing period.
     */
    UNKNOWN
}
