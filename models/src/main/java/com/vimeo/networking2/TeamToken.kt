package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Token of Magisto team.
 *
 * @param token The token of Magisto team.
 */
@JsonClass(generateAdapter = true)
data class TeamToken(

    @Json(name = "magisto_access_token")
    val token: String? = null

)
