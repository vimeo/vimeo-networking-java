package com.vimeo.networking2

/**
 * Based on CAPABILITY_LIVE_EVENT_CHAT.
 */
data class LiveChat(

    /**
     * The identification number of the live clip's chat room.
     */
    val roomId: String?,

    /**
     * The JSON Web Token to access the live clip's chat room.
     */
    val token: String?,

    /**
     * User.
     */
    val user: User?

)
