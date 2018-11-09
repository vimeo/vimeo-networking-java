package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal
import com.vimeo.networking2.enums.VideoPlayStatus

/**
 * Play information.
 */
@JsonClass(generateAdapter = true)
data class Play(

    /**
     * The DASH video file.
     */
    @Internal
    @Json(name = "dash")
    val dash: VideoFile? = null,

    /**
     * HLS video files.
     */
    @Internal
    @Json(name = "hls")
    val hls: VideoFile? = null,

    /**
     * The play progress in seconds.
     */
    @Internal
    @Json(name = "progress")
    val progress: PlayProgress? = null,

    /**
     * Progressive files.
     */
    @Json(name = "progressive")
    val progressive: List<ProgressiveFileType>? = null,

    /**
     * The source file of the video.
     */
    @Internal
    @Json(name = "source")
    val source: List<VideoSourceFile>? = null,

    /**
     * The play status of the video.
     */
    @Json(name = "status")
    val status: VideoPlayStatus? = null
)
