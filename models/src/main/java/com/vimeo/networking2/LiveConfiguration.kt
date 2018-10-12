package com.vimeo.networking2
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Live Streaming configuration data.
 *
 * Requires [CapabilitiesType.CAPABILITY_PLATFORM_CONFIGS_OTA_LIVE].
 */
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
