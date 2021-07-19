package com.vimeo.networking2.params

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * An object that is used to patch video addition updates to an Album.
 *
 * @param uri The URI of the video.
 */
@JsonClass(generateAdapter = true)
data class RemoveVideoFromAlbum(

    @Json(name = "uri")
    val uri: String
)
