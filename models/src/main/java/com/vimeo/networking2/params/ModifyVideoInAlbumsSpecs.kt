package com.vimeo.networking2.params

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * An object that is used to patch video addition and deletion updates to an Album.
 *
 * @param removeVideoSet The videos which should be removed.
 * @param addVideoSet The videos which should be added.
 */
@JsonClass(generateAdapter = true)
data class ModifyVideoInAlbumsSpecs(
    @Json(name = "remove")
    val removeVideoSet: Set<RemoveVideoFromAlbum>? = null,

    @Json(name = "add")
    val addVideoSet: Set<AddVideoToAlbum>? = null
)
