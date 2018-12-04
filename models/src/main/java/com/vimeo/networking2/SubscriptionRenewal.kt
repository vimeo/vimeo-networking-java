package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Information about the user's next renewal.
 */
@JsonClass(generateAdapter = true)
data class SubscriptionRenewal(

    /**
     * The date in YYYY-MM-DD format when the user's membership renews (or expires, if they have
     * disabled autorenew). For display only.
     */
    @Json(name = "display_date")
    val displayDate: String

)
