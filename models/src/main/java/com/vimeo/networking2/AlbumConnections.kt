package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

/**
 * All connections for a album.
 */
@JsonClass(generateAdapter = true)
data class AlbumConnections(

        /**
         * Connection to get all the videos in a album.
         */
        @Json(name = "videos")
        val videos: Connection? = null,

        /**
         * Connection to get all the logged-in user's available videos that can be added to an album.
         */
        @Json(name = "available_videos")
        val availableVideos: Connection? = null

) : Serializable {
    companion object {
        private const val serialVersionUID = -295177015439L
    }
}
