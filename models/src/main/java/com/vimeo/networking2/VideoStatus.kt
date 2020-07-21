package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.VideoStateType
import com.vimeo.networking2.enums.asEnum

/**
 * The current status of the video transcoding process.
 */
@JsonClass(generateAdapter = true)
data class VideoStatus(
    /**
     * The current state of the transcoding process.
     */
    @Json(name = "state")
    val state: String? = null,

    /**
     * The percentage of the transcoding process that is complete.
     */
    @Json(name = "progress")
    val progress: Int? = null,

    /**
     * The remaining time in seconds before transcoding is complete.
     */
    @Json(name = "time_left")
    val timeLeft: Long? = null
)

/**
 * @see VideoStatus.state
 * @see VideoStateType
 */
val VideoStatus.videoStateType: VideoStateType
    get() = state.asEnum(VideoStateType.UNKNOWN)
