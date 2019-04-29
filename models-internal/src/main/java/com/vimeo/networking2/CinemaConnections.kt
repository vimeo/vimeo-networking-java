package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * All connections for a cinema.
 */
@JsonClass(generateAdapter = true)
data class CinemaConnections(

    /**
     * Information about the contents of this programmed cinema item.
     */
    @Json(name = "contents")
    val contents: Connection? = null

)
