package com.vimeo.networking2.params

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Container for [RemoveVideoFromAlbum].
 *
 * @param video The video that should be removed.
 */
@JsonClass(generateAdapter = true)
data class RemoveVideoFromAlbumContainer(

    @Json(name = "video")
    val video: RemoveVideoFromAlbum
) {
    /**
     * A secondary constructor that eliminates the redundancy of the wrapping.
     *
     * @param uri The URI of the video.
     */
    constructor(uri: String) : this(RemoveVideoFromAlbum(uri))
}
