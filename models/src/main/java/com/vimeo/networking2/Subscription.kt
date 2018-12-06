package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Information about the user's subscription.
 */
@JsonClass(generateAdapter = true)
data class Subscription(

    /**
     * Information about the user's next renewal.
     */
    @Json(name = "renewal")
    val renewal: SubscriptionRenewal? = null,

    /**
     * Information about the user's trial period.
     */
    @Json(name = "trial")
    val trial: SubscriptionTrial? = null
)
