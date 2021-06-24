@file:JvmName("PlayUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal
import com.vimeo.networking2.enums.VideoPlayStatusType
import com.vimeo.networking2.enums.asEnum

/**
 * Play information.
 *
 * @param dash The DASH video file.
 * @param hls HLS video files.
 * @param progress The play progress in seconds.
 * @param progressive Progressive files.
 * @param source The source file of the video.
 * @param videoPlayStatus The play status of the video. See [Play.videoPlayStatusType].
 * @param drm The DRM play data for a protected stream.
 */
@Internal
@JsonClass(generateAdapter = true)
data class Play(

    @Internal
    @Json(name = "dash")
    val dash: DashVideoFile? = null,

    @Internal
    @Json(name = "hls")
    val hls: HlsVideoFile? = null,

    @Internal
    @Json(name = "progress")
    val progress: PlayProgress? = null,

    @Deprecated("Use the download property on a Video instead")
    @Internal
    @Json(name = "progressive")
    val progressive: List<ProgressiveVideoFile>? = null,

    @Internal
    @Json(name = "source")
    val source: List<VideoSourceFile>? = null,

    @Internal
    @Json(name = "status")
    val videoPlayStatus: String? = null,

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
