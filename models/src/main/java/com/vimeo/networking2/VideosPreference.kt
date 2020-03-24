package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

/**
 * Default preferences that a user has for their videos.
 */
@JsonClass(generateAdapter = true)
data class VideosPreference(

        /**
         * Privacy values for videos.
         */
        @Json(name = "privacy")
        val privacy: Privacy? = null

) : Serializable {

    companion object {
        private const val serialVersionUID = -1236876L
    }
}
