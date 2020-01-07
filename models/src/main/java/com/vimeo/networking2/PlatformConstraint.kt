package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by nicholas.doglio on 2020-01-07.
 */
@JsonClass(generateAdapter = true)
data class PlatformConstraint(
        @Json(name = "duration")
        val duration: Int = 0,
        @Json(name = "size")
        val size: Long = 0
)