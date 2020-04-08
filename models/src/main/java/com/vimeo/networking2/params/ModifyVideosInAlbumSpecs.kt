package com.vimeo.networking2.params

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * An object that is used to patch video addition and deletion updates to an Album.
 */
@JsonClass(generateAdapter = true)
data class ModifyVideosInAlbumSpecs(
    /**
     * The set of videos that should be removed.
     */
    @Json(name = "remove")
    val removeVideoSet: Set<RemoveVideoFromAlbumContainer>? = null,

    /**
     * The set of videos that should be added.
     */
    @Json(name = "set")
    val addVideoSet: Set<AddVideoToAlbumContainer>? = null

)
