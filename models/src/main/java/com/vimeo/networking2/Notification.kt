package com.vimeo.networking2

import com.vimeo.networking2.enums.NotificationType
import com.vimeo.networking2.enums.NotificationType.UNKNOWN
import java.util.*

/**
 * Notification data.
 */
data class Notification(

    /**
     * The clip associated with a clip like notification.
     */
    val clip: Video? = null,

    /**
     * The clip comment associated with a comment notification.
     */
    val comment: Comment? = null,

    /**
     * The ISODate time a notification was created.
     */
    val createdTime: Date? = null,

    /**
     * The clip credit associated with a credit notification.
     */
    val credit: Credit? = null,

    /**
     * Is the notification marked as new.
     */
    val new: Boolean? = null,

    /**
     * Is the notification marked as seen.
     */
    val seen: Boolean? = null,

    /**
     * The type of notification.
     */
    val type: NotificationType = UNKNOWN,

    /**
     * The notification's canonical relative URI.
     */
    val uri: String? = null,

    /**
     * The user associated with a user follow notification.
     */
    val user: User? = null

)
