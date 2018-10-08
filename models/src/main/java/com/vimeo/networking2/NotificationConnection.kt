package com.vimeo.networking2

/**
 * Based on CAPABILITY_APP_NOTIFICATIONS.
 */
data class NotificationConnection(

    /**
     * The total number of new notifications. This data requires a bearer token
     * with the private scope.
     */
    val newTotal: Int = 0,

    /**
     * The total number of notifications. This data requires a bearer token with
     * the private scope.
     */
    val total: Int = 0,

    /**
     * The total number of new notifications. This data requires a bearer
     * token with the private scope.
     */
    val totalNew: Int = 0,

    /**
     * Information about this user's notifications. This data requires a bearer token
     * with the private scope.
     *
     * Based on CAPABILITY_APP_NOTIFICATIONS.
     */
    val typeCount: NotificationTypeCount? = null,

    /**
     * An array of notification types and the total number of unseen notifications.
     *
     * Based on CAPABILITY_APP_NOTIFICATIONS.
     */
    val typeUnseenCount: NotificationTypeCount? = null,

    /**
     * The total number of unread notifications.
     */
    val unreadTotal: Int = 0,

    /**
     * The API URI that resolves to the connection data.
     */
    val uri: String? = null

)

