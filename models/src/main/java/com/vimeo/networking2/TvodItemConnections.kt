package com.vimeo.networking2

/**
 * All of [TVodItem]'s connections.
 */
data class TvodItemConnections(

    /**
     * Information about the comments associated with this page.
     */
    val comments: Connection? = null,

    /**
     * Information about the genres associated with this page.
     */
    val genres: Connection? = null,

    /**
     * Information about the likes associated with this page.
     */
    val likes: Connection? = null,

    /**
     * Information about the pictures associated with this page.
     */
    val pictures: Connection? = null,

    /**
     * Information about the seasons associated with this page.
     */
    val seasons: Connection? = null,

    /**
     * Information about the videos associated with this page.
     */
    val videos: VideosTvodItemConnection? = null

)
