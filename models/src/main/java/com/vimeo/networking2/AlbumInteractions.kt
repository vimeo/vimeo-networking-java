package com.vimeo.networking2

/**
 * All actions that can be taken on albums.
 */
data class AlbumInteractions(

    /**
     * An action indicating that the authenticated user is an admin of the album and may therefore
     * add custom logos. This data requires a bearer token with the private scope.
     */
    val addLogos: BasicInteraction? = null,

    /**
     * An action indicating that the authenticated user is an admin of the album and may therefore
     * add videos. This data requires a bearer token with the private scope.
     */
    val addVideos: BasicInteraction? = null

)
