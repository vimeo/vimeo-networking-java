package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

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

)
