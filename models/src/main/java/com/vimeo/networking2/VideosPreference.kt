package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Default preferences that a user has for their videos.
 *
 * @param privacy Privacy values for videos.
 */
@JsonClass(generateAdapter = true)
data class VideosPreference(

    @Json(name = "privacy")
    val privacy: Privacy? = null

)
