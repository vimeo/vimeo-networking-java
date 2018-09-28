package com.vimeo.networking2

data class Cinema(

    /**
     * The category associated with this programmed cinema item.
     */
    val category: Category?,

    /**
     * The channel associated with this programmed cinema item.
     */
    val channel: Channel?,

    /**
     * Content for the programmed cinema item.
     */
    val content: List<Video>?,

    /**
     * Cinema metadata.
     */
    val metadata: CinemaMetadata?,

    /**
     * The name of the programmed cinema item.
     */
    val name: String?,

    /**
     * The type of programmed cinema item.
     */
    val type: CinemaType?,

    /**
     * The programmed cinema items' canonical relative URI.
     */
    val uri: String?

)
