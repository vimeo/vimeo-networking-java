package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Requires [CapabilitiesType.CAPABILITY_LIVE_EVENT_CHAT].
 */
@JsonClass(generateAdapter = true)
data class LiveChat(

    /**
     * The identification number of the live clip's chat room.
     */
    @Json(name = "room_id")
    val roomId: String? = null,

    /**
     * The JSON Web Token to access the live clip's chat room.
     */
    @Json(name = "token")
    val token: String? = null,

    /**
     * User.
     */
    @Json(name = "user")
    val user: User? = null

)
