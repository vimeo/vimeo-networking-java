@file:JvmName("BillingUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.BillingStatusType
import com.vimeo.networking2.enums.asEnum

/**
 * The Billing information for this subscription.
 *
 * @param status The user's billing information status. See [Billing.statusType].
 */
@JsonClass(generateAdapter = true)
data class Billing(

    @Json(name = "status")
    val status: String? = null
)

/**
 * @see [Billing.status]
 * @see BillingStatusType
 */
val Billing.statusType: BillingStatusType
    get() = status.asEnum(BillingStatusType.UNKNOWN)
