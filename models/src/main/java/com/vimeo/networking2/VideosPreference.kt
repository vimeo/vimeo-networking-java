package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

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

)
