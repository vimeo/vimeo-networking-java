package com.vimeo.networking2

/**
 * Requires [CapabilitiesType.CAPABILITY_LIVE_EVENT_CHAT].
 */
data class LiveChat(

    /**
     * The identification number of the live clip's chat room.
     */
    val roomId: String? = null,

    /**
     * The JSON Web Token to access the live clip's chat room.
     */
    val token: String? = null,

    /**
     * User.
     */
    val user: User? = null

)
