package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * All connections for a album.
 *
 * @param videos Connection to get all the videos in a album.
 * @param availableVideos Connection to get all the logged-in user's available videos that can be added to an album.
 */
@JsonClass(generateAdapter = true)
data class AlbumConnections(

    @Json(name = "videos")
    val videos: BasicConnection? = null,

    @Json(name = "available_videos")
    val availableVideos: BasicConnection? = null
)
