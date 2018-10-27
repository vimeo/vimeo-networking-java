package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Requires [CapabilitiesType.CAPABILITY_APP_NOTIFICATIONS].
 */
@JsonClass(generateAdapter = true)
data class NotificationConnection(

    /**
     * The total number of new notifications. This data requires a bearer token
     * with the private scope.
     */
    @Json(name = "new_total")
    val newTotal: Int? = null,

    /**
     * The total number of notifications. This data requires a bearer token with
     * the private scope.
     */
    @Json(name = "total")
    val total: Int? = null,

    /**
     * Information about this user's notifications. This data requires a bearer token
     * with the private scope.
     *
     * Requires [CapabilitiesType.CAPABILITY_APP_NOTIFICATIONS].
     */
    @Json(name = "type_count")
    val typeCount: NotificationTypeCount? = null,

    /**
     * An array of notification types and the total number of unseen notifications.
     *
     * Requires [CapabilitiesType.CAPABILITY_APP_NOTIFICATIONS].
     */
    @Json(name = "type_unseen_count")
    val typeUnseenCount: NotificationTypeCount? = null,

    /**
     * The total number of unread notifications.
     */
    @Json(name = "unread_total")
    val unreadTotal: Int? = null,

    /**
     * The API URI that resolves to the connection data.
     */
    @Json(name = "uri")
    val uri: String? = null

)
