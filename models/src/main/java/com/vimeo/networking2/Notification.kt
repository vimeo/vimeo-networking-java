@file:JvmName("NotificationUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.NotificationType
import com.vimeo.networking2.enums.asEnum
import java.util.Date

/**
 * Notification data.
 *
 * @param video The video associated with a video like notification.
 * @param comment The clip comment associated with a comment notification.
 * @param createdTime The ISODate time a notification was created.
 * @param credit The clip credit associated with a credit notification.
 * @param new Whether or not the notification is marked as new.
 * @param seen Whether or not the notification is marked as seen.
 * @param rawType The type of notification. See [Notification.type].
 * @param uri The notification's canonical relative URI.
 * @param user The user associated with a user follow notification.
 */
@JsonClass(generateAdapter = true)
data class Notification(

    @Json(name = "clip")
    val video: Video? = null,

    @Json(name = "comment")
    val comment: Comment? = null,

    @Json(name = "created_time")
    val createdTime: Date? = null,

    @Json(name = "credit")
    val credit: Credit? = null,

    @Json(name = "new")
    val new: Boolean? = null,

    @Json(name = "seen")
    val seen: Boolean? = null,

    @Json(name = "type")
    val rawType: String? = null,

    @Json(name = "uri")
    val uri: String? = null,

    @Json(name = "user")
    val user: User? = null

)

/**
 * @see Notification.rawType
 * @see NotificationType
 */
val Notification.type: NotificationType
    get() = rawType.asEnum(NotificationType.UNKNOWN)
