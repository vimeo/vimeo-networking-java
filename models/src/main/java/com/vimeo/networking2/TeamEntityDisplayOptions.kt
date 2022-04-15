package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Represents any client side display characteristics which are dictated by the server, or backend.
 *
 * @param color Shows the tint or background color used against a view element representing a team entity. Likely some
 * manner of icon
 */
@JsonClass(generateAdapter = true)
data class TeamEntityDisplayOptions(
    @Json(name = "color")
    val color: String
)
