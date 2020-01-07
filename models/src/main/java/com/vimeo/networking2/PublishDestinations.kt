package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by nicholas.doglio on 2020-01-07.
 */
@JsonClass(generateAdapter = true)
data class PublishDestinations(
        @Json(name = "facebook")
        val facebook: Boolean = false,
        @Json(name = "youtube")
        val youtube: Boolean = false,
        @Json(name = "linkedin")
        val linkedin: Boolean = false,
        @Json(name = "twitter")
        val twitter: Boolean = false
)
