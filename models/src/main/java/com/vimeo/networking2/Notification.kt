package com.vimeo.networking2

import java.util.*

data class Notification(

    /**
     * The clip associated with a clip like notification.
     */
    val clip: Video?,

    /**
     * The clip comment associated with a comment notification.
     */
    val comment: Comment?,

    /**
     * The ISODate time a notification was created.
     */
    val createdTime: Date?,

    /**
     * The clip credit associated with a credit notification.
     */
    val credit: Credit?,

    /**
     * Is the notification marked as new.
     */
    val new: Boolean?,

    /**
     * Is the notification marked as seen.
     */
    val seen: Boolean?,

    /**
     * The type of notification.
     */
    val type: NotificationType?,

    /**
     * The notification's canonical relative URI.
     */
    val uri: String?,

    /**
     * The user associated with a user follow notification.
     */
    val user: User?

)
