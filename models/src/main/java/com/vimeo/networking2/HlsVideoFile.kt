package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal
import com.vimeo.networking2.common.VideoFile
import java.util.*

/**
 * Video file data.
 */
@Internal
@JsonClass(generateAdapter = true)
data class HlsVideoFile(

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
    val live: LiveHeartbeat? = null
) : VideoFile
