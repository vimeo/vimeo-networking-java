package com.vimeo.networking2

/**
 * Live heart beat configuration.
 *
 * Requires [CapabilitiesType.CAPABILITY_PLATFORM_CONFIGS_OTA_LIVE].
 */
data class LiveHeartbeatConfiguration(

    /**
     * Is live heartbeat logging enabled? If it is enabled, then mobile apps should send a
     * heartbeat log, play.{hls|dash}.live.heartbeat, so we can track the amount of concurrent
     * users viewing a stream.
     */
    val enabled: Boolean? = null,

    /**
     * The interval, in seconds, at which a live heartbeat should be sent.
     */
    val interval: Int?

)
