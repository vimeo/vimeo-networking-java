package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * All connections for a album.
 */
@JsonClass(generateAdapter = true)
data class AlbumConnections(

    /**
     * Connection to get all the videos in a album.
     */
    @Json(name = "videos")
    val videos: Connection? = null

)
