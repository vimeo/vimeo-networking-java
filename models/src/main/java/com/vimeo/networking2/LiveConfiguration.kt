package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal

/**
 * Live Streaming configuration data.
 *
 * @param chat Live chat configuration data.
 * @param heartbeat Live heart beat configuration data.
 */
@Internal
@JsonClass(generateAdapter = true)
data class LiveConfiguration(

    @Internal
    @Json(name = "chat")
    val chat: LiveChatConfiguration? = null,

    @Internal
    @Json(name = "heartbeat")
    val heartbeat: LiveHeartbeatConfiguration? = null

)
