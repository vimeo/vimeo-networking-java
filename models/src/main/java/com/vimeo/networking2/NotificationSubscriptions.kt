package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.Date

/**
 * A collection of push notifications the user is subscribed to.
 *
 * @param modifiedTime The ISODate time the settings were modified.
 * @param subscriptions The settings for each notification subscription.
 * @param uri The subscription settings' canonical relative URI.
 */
@JsonClass(generateAdapter = true)
data class NotificationSubscriptions(

    @Json(name = "modified_time")
    val modifiedTime: Date? = null,

    @Json(name = "subscriptions")
    val subscriptions: Subscriptions? = null,

    @Json(name = "uri")
    val uri: String? = null

)
