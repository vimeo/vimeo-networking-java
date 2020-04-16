package com.vimeo.networking2.params

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Representation of a video that should be added to an album.
 */
@JsonClass(generateAdapter = true)
data class AddVideoToAlbum(
    /**
     * The URI of the video.
     */
    @Json(name = "uri")
    val uri: String,

    /**
     * The position of the video.
     */
    @Json(name = "position")
    val position: Int?
)
