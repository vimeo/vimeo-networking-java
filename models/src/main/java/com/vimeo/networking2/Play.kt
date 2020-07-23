@file:JvmName("PlayUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal
import com.vimeo.networking2.enums.VideoPlayStatusType
import com.vimeo.networking2.enums.asEnum

/**
 * Play information.
 */
@Internal
@JsonClass(generateAdapter = true)
data class Play(

    /**
     * The DASH video file.
     */
    @Internal
    @Json(name = "dash")
    val dash: DashVideoFile? = null,

    /**
     * HLS video files.
     */
    @Internal
    @Json(name = "hls")
    val hls: HlsVideoFile? = null,

    /**
     * The play progress in seconds.
     */
    @Internal
    @Json(name = "progress")
    val progress: PlayProgress? = null,

    /**
     * Progressive files.
     */
    @Internal
    @Json(name = "progressive")
    val progressive: List<ProgressiveVideoFile>? = null,

    /**
     * The source file of the video.
     */
    @Internal
    @Json(name = "source")
    val source: List<VideoSourceFile>? = null,

    /**
     * The play status of the video.
     * @see Play.videoPlayStatusType
     */
    @Internal
    @Json(name = "status")
    val videoPlayStatus: String? = null,

    /**
     * The DRM play data for a protected stream.
     */
    @Internal
    @Json(name = "drm")
    val drm: Drm? = null
)

/**
 * @see Play.videoPlayStatus
 * @see VideoPlayStatusType
 */
val Play.videoPlayStatusType: VideoPlayStatusType
    get() = videoPlayStatus.asEnum(VideoPlayStatusType.UNKNOWN)
