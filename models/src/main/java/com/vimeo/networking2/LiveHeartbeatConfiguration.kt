package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Live heart beat configuration.
 *
 * Requires [CapabilitiesType.CAPABILITY_PLATFORM_CONFIGS_OTA_LIVE].
 */
@JsonClass(generateAdapter = true)
data class LiveHeartbeatConfiguration(

    /**
     * Is live heartbeat logging enabled? If it is enabled, then mobile apps should send a
     * heartbeat log, play.{hls|dash}.live.heartbeat, so we can track the amount of concurrent
     * users viewing a stream.
     */
    @Json(name = "enabled")
    val enabled: Boolean? = null,

    /**
     * The interval, in seconds, at which a live heartbeat should be sent.
     */
    @Json(name = "interval")
    val interval: Int? = null

)
