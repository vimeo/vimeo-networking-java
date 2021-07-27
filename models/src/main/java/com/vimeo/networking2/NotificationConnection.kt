package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal
import com.vimeo.networking2.common.Connection

/**
 * All actions that can be taken on notifications.
 *
 * @param newTotal The total number of new notifications. This data requires a bearer token with the private scope.
 * @param total The total number of notifications. This data requires a bearer token with the private scope.
 * @param typeCount Information about this user's notifications. This data requires a bearer token with the private
 * scope.
 * @param typeUnseenCount An array of notification types and the total number of unseen notifications.
 * @param unreadTotal The total number of unread notifications.
 */
@Internal
@JsonClass(generateAdapter = true)
data class NotificationConnection(

    @Internal
    @Json(name = "new_total")
    val newTotal: Int? = null,

    @Internal
    @Json(name = "total")
    val total: Int? = null,

    @Internal
    @Json(name = "type_count")
    val typeCount: NotificationTypeCount? = null,

    @Internal
    @Json(name = "type_unseen_count")
    val typeUnseenCount: NotificationTypeCount? = null,

    @Internal
    @Json(name = "unread_total")
    val unreadTotal: Int? = null,

    @Internal
    @Json(name = "uri")
    override val uri: String? = null,

    @Internal
    @Json(name = "options")
    override val options: List<String>? = null

) : Connection
