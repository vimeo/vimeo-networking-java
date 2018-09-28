package com.vimeo.networking2

/**
 * Based on CAPABILITY_PLATFORM_CONFIGS_OTA_LIVE.
 */
data class LiveConfiguration(

    /**
     * Live chat configuration data.
     */
    val chat: LiveChatConfiguration,

    /**
     * Live heart beat configuration data.
     */
    val heartbeat: LiveHeartbeatConfiguration


)
