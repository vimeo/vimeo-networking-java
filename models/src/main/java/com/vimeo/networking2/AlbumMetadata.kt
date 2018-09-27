package com.vimeo.networking2

data class AlbumMetadata(

    /**
     * A collection of information that is connected to this resource.
     */
    val connections: AlbumConnections?,

    /**
     * A collection of interactions for this resource.
     */
    val interactions: AlbumInteractions?

)
