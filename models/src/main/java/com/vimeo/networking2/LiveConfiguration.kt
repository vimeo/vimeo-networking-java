package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal

/**
 * Live Streaming configuration data.
 */
@Internal
@JsonClass(generateAdapter = true)
data class LiveConfiguration(

    /**
     * Live chat configuration data.
     */
    @Json(name = "chat")
    val chat: LiveChatConfiguration? = null,

    /**
     * Live heart beat configuration data.
     */
    @Json(name = "heartbeat")
    val heartbeat: LiveHeartbeatConfiguration? = null


)
