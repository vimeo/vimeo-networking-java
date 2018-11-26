package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * All of [Season]'s connections.
 */
@JsonClass(generateAdapter = true)
data class SeasonConnections(

    /**
     * The Videos connection.
     */
    @Json(name = "videos")
    val videos: Connection? = null

)
