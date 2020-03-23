package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal
import com.vimeo.networking2.common.VideoFile
import java.util.*

/**
 * A video file that represents a DASH stream.
 */
@JsonClass(generateAdapter = true)
data class DashVideoFile(

    @Internal
    @Json(name = "link")
    override val link: String? = null,

    @Internal
    @Json(name = "link_expiration_time")
    override val linkExpirationTime: Date? = null,

    @Internal
    @Json(name = "log")
    override val log: String? = null,

    /**
     * The info about the live heartbeat endpoint, used if the video is a live video.
     */
    @Internal
    @Json(name = "live")
    val live: LiveHeartbeat? = null,

    /**
     * The token used for DRM protected streams.
     */
    @Internal
    @Json(name = "token")
    val token: String? = null,

    /**
     * The license link for DRM protected streams.
     */
    @Internal
    @Json(name = "license_link")
    val licenseLink: String? = null
) : VideoFile
