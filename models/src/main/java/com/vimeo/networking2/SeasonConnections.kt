package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * All of [Season]'s connections.
 *
 * @param videos The Videos connection.
 */
@JsonClass(generateAdapter = true)
data class SeasonConnections(

    @Json(name = "videos")
    val videos: BasicConnection? = null

)
