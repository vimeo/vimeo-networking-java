package com.vimeo.networking2

/**
 * List of text tracks for a video.
 */
data class TextTrackList(

    /**
     * Total number of text tracks.
     */
    val total: Int? = null,

    /**
     * List of all the text tracks for a video.
     */
    val textTracks: List<TextTrack>? = null

)
