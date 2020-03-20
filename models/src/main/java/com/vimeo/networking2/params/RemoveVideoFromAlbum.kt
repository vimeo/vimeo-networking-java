package com.vimeo.networking2.params

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * An object that is used to patch video addition updates to an Album.
 */
@JsonClass(generateAdapter = true)
data class RemoveVideoFromAlbum(
    /**
     * The URI of the video.
     */
    @Json(name = "uri")
    val uri: String
)
