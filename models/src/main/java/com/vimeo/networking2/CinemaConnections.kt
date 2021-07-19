package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * All connections for a cinema.
 *
 * @param contents Information about the contents of this programmed cinema item.
 */
@JsonClass(generateAdapter = true)
data class CinemaConnections(

    @Json(name = "contents")
    val contents: BasicConnection? = null

)
