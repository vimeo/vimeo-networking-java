package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.VideoStateType
import com.vimeo.networking2.enums.asEnum
import java.io.Serializable

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
        val progress: Int? = 0,

        /**
         * The remaining time in seconds before transcoding is complete.
         */
        @Json(name = "time_left")
        val timeLeft: Long? = 0
) : Serializable {

    companion object {
        private const val serialVersionUID = -72138L
    }
}

/**
 * @see VideoStatus.state
 * @see VideoStateType
 */
val VideoStatus.videoStateType: VideoStateType
        get() = state.asEnum(VideoStateType.UNKNOWN)
