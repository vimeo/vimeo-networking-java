package com.vimeo.networking2.params

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Container for [RemoveVideoFromAlbum].
 */
@JsonClass(generateAdapter = true)
data class NamedWrapperForRemove(
    /**
     * The video that should be removed.
     */
    @Json(name = "video")
    val video: RemoveVideoFromAlbum
)
