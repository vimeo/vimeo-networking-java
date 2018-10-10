package com.vimeo.networking2

/**
 * Live Streaming configuration data.
 *
 * Requires [CapabilitiesType.CAPABILITY_PLATFORM_CONFIGS_OTA_LIVE].
 */
data class LiveConfiguration(

    /**
     * Live chat configuration data.
     */
    val chat: LiveChatConfiguration? = null,

    /**
     * Live heart beat configuration data.
     */
    val heartbeat: LiveHeartbeatConfiguration? = null


)
