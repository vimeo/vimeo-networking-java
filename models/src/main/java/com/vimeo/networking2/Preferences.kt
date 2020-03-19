package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

/**
 * Preferences that may have been set by a user.
 */
@JsonClass(generateAdapter = true)
data class Preferences(

    /**
     * Video preferences set by the a user.
     */
    @Json(name = "videos")
    val videos: VideosPreference? = null

): Serializable {

    companion object {
        private const val serialVersionUID = -113L
    }
}
