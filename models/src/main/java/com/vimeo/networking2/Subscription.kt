package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

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
) : Serializable {

    companion object {
        private const val serialVersionUID = -51L
    }
}
