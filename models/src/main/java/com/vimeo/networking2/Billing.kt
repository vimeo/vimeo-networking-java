package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.BillingStatusType
import com.vimeo.networking2.enums.asEnum

/**
 * The Billing information for this membership.
 */
@JsonClass(generateAdapter = true)
data class Billing(
    /**
     * The user's billing information status.
     */
    @Json(name = "status")
    val status: String
)

/**
 * @see [Billing.status]
 * @see BillingStatusType
 */
val Billing.statusType: BillingStatusType
    get() = status.asEnum(BillingStatusType.UNKNOWN)
