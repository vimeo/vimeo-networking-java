package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal

/**
 * A model representing the specific data needed for the live chat feature when a live video is playing.
 * Additional data can be found in the [LiveChatConfiguration] class available in the [AppConfiguration].
 */
@Internal
@JsonClass(generateAdapter = true)
data class LiveChat(

    /**
     * The identification number of the live clip's chat room.
     */
    @Internal
    @Json(name = "room_id")
    val roomId: String? = null,

    /**
     * The JSON Web Token to access the live clip's chat room.
     */
    @Internal
    @Json(name = "token")
    val token: String? = null,

    /**
     * User.
     */
    @Internal
    @Json(name = "user")
    val user: User? = null

)
