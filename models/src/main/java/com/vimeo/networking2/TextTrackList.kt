package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * List of text tracks for a video.
 */
@JsonClass(generateAdapter = true)
data class TextTrackList(

    /**
     * Total number of text tracks.
     */
    @Json(name = "total")
    val total: Int? = null,

    /**
     * List of all the text tracks for a video.
     */
    @Json(name = "data")
    val textTracks: List<TextTrack>? = null

)
