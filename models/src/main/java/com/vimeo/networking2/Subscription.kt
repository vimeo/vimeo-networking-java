package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Information about the user's subscription.
 *
 * @param renewal Information about the user's next renewal.
 * @param trial Information about the user's trial period.
 * @param billing Information about the user's billing info.
 */
@JsonClass(generateAdapter = true)
data class Subscription(

    @Json(name = "renewal")
    val renewal: SubscriptionRenewal? = null,

    @Json(name = "trial")
    val trial: SubscriptionTrial? = null,

    @Json(name = "billing")
    val billing: Billing? = null
)
