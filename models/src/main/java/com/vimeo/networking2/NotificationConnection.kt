package com.vimeo.networking2

/**
 * Requires [CapabilitiesType.CAPABILITY_APP_NOTIFICATIONS].
 */
data class NotificationConnection(

    /**
     * The total number of new notifications. This data requires a bearer token
     * with the private scope.
     */
    val newTotal: Int? = null,

    /**
     * The total number of notifications. This data requires a bearer token with
     * the private scope.
     */
    val total: Int? = null,

    /**
     * Information about this user's notifications. This data requires a bearer token
     * with the private scope.
     *
     * Requires [CapabilitiesType.CAPABILITY_APP_NOTIFICATIONS].
     */
    val typeCount: NotificationTypeCount? = null,

    /**
     * An array of notification types and the total number of unseen notifications.
     *
     * Requires [CapabilitiesType.CAPABILITY_APP_NOTIFICATIONS].
     */
    val typeUnseenCount: NotificationTypeCount? = null,

    /**
     * The total number of unread notifications.
     */
    val unreadTotal: Int? = null,

    /**
     * The API URI that resolves to the connection data.
     */
    val uri: String? = null

)
