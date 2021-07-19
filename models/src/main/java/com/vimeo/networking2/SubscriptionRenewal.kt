package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.Date

/**
 * Information about the user's next renewal.
 *
 * @param displayDate The date in YYYY-MM-DD format when the user's membership renews (or expires, if they have disabled
 * autorenew). For display only.
 * @param renewalDate The date the user's membership renews (or expires, if they have disabled autorenew).
 */
@JsonClass(generateAdapter = true)
data class SubscriptionRenewal(

    @Json(name = "display_date")
    val displayDate: String? = null,

    @Json(name = "renewal_date")
    val renewalDate: Date? = null
)
