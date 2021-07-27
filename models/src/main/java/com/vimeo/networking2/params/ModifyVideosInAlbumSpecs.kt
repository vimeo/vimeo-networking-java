package com.vimeo.networking2.params

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * An object that is used to patch video addition and deletion updates to an Album.
 *
 * @param removeVideoSet The set of videos that should be removed.
 * @param addVideoSet The set of videos that should be added.
 */
@JsonClass(generateAdapter = true)
data class ModifyVideosInAlbumSpecs(
    @Json(name = "remove")
    val removeVideoSet: Set<RemoveVideoFromAlbumContainer>? = null,

    @Json(name = "set")
    val addVideoSet: Set<AddVideoToAlbumContainer>? = null
)
