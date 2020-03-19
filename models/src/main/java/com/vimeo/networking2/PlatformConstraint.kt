package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

/**
 * Constraints put in place by a social media platform on uploading videos.
 */
@JsonClass(generateAdapter = true)
data class PlatformConstraint(
    /**
     * The max length in seconds of a video for the corresponding platform.
     */
    @Json(name = "duration")
    val duration: Int? = null,

    /**
     * The max file size in gigabytes of a video for the corresponding platform.
     */
    @Json(name = "size")
    val size: Long? = null
): Serializable {

    companion object {
        private const val serialVersionUID = -116L
    }
}
