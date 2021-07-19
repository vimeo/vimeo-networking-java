package com.vimeo.networking2.params

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Representation of a video that should be added to an album.
 *
 * @param uri The URI of the video.
 * @param position The position of the video.
 */
@JsonClass(generateAdapter = true)
data class AddVideoToAlbum(
    @Json(name = "uri")
    val uri: String,

    @Json(name = "position")
    val position: Int?
)
