package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.*

/**
 * A collection of push notifications the user is subscribed to.
 */
@JsonClass(generateAdapter = true)
data class NotificationSubscriptions(

    /**
     * The ISODate time the settings were modified.
     */
    @Json(name = "modified_time")
    val modifiedTime: Date? = null,

    /**
     * The settings for each notification subscription.
     */
    @Json(name = "subscriptions")
    val subscriptions: Subscriptions? = null,

    /**
     * The subscription settings' canonical relative URI.
     */
    @Json(name = "uri")
    val uri: String? = null

)
