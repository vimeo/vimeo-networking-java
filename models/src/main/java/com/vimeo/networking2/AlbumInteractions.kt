package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * All actions that can be taken on albums.
 */
@JsonClass(generateAdapter = true)
data class AlbumInteractions(

    /**
     * An action indicating that the authenticated user is an admin of the album and may therefore
     * add custom logos. This data requires a bearer token with the private scope.
     */
    @Json(name = "add_logos")
    val addLogos: BasicInteraction? = null,

    /**
     * An action indicating that the authenticated user is an admin of the album and may therefore
     * add videos. This data requires a bearer token with the private scope.
     */
    @Json(name = "add_videos")
    val addVideos: BasicInteraction? = null

)
