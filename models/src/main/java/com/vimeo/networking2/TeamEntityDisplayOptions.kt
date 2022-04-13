package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TeamEntityDisplayOptions(
    @Json(name = "color")
    val color: String
)