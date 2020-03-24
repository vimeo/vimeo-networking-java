package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

/**
 * Video logging data.
 */
@JsonClass(generateAdapter = true)
data class VideoLog(

    /**
     * The URL to record a Play logging event.
     */
    @Json(name = "play")
    val play: String? = null

) : Serializable {

    companion object {
        private const val serialVersionUID = -125L
    }
}
