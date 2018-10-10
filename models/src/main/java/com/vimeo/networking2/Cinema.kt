package com.vimeo.networking2

import com.vimeo.networking2.enums.CinemaType

/**
 * Cinema data.
 */
data class Cinema(

    /**
     * The category associated with this programmed cinema item.
     */
    val category: Category? = null,

    /**
     * The channel associated with this programmed cinema item.
     */
    val channel: Channel? = null,

    /**
     * Content for the programmed cinema item.
     */
    val content: List<Video>? = null,

    /**
     * Cinema metadata.
     */
    val metadata: MetadataConnections<CinemaConnections>? = null,

    /**
     * The name of the programmed cinema item.
     */
    val name: String? = null,

    /**
     * The type of programmed cinema item.
     */
    val type: CinemaType? = null,

    /**
     * The programmed cinema items' canonical relative URI.
     */
    val uri: String? = null

)
