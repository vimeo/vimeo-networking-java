package com.vimeo.networking2.params

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Container for [AddVideoToAlbum].
 */
@JsonClass(generateAdapter = true)
data class NamedWrapperForAdd(
    /**
     * The video that should be added.
     */
    @Json(name = "video")
    val video: AddVideoToAlbum
)
