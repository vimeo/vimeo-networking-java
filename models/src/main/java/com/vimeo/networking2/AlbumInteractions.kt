package com.vimeo.networking2

data class AlbumInteractions(

    /**
     * An action indicating that the authenticated user is an admin of the album and may therefore
     * add custom logos. This data requires a bearer token with the private scope.
     */
    val addLogos: Interaction? = null,

    /**
     * An action indicating that the authenticated user is an admin of the album and may therefore
     * add videos. This data requires a bearer token with the private scope.
     */
    val addVideos: Interaction? = null

)
