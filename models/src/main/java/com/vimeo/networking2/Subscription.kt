package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Information about the user's subscription.
 */
@JsonClass(generateAdapter = true)
data class Subscription(

    @Json(name = "renewal")
    val renewal: SubscriptionRenewal,

    @Json(name = "trial")
    val trial: SubscriptionTrial? = null
)
