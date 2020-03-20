package com.vimeo.networking2.params

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * An object that is used to patch video addition and deletion updates to an Album.
 */
@JsonClass(generateAdapter = true)
data class ModifyVideoInAlbumsSpecs(
    /**
     * The videos which should be removed.
     */
    @Json(name = "remove")
    val removeVideoSet: Set<RemoveVideoFromAlbum>? = null,

    /**
     * The videos which should be added.
     */
    @Json(name = "add")
    val addVideoSet: Set<AddVideoToAlbum>? = null
)
