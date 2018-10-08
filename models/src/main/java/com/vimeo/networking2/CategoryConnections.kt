package com.vimeo.networking2

data class CategoryConnections(

    /**
     * Information about the channels related to this category.
     */
    val channels: Connection? = null,

    /**
     * Information about the groups related to this category.
     */
    val groups: Connection? = null,

    /**
     * Information about the users related to this category.
     */
    val users: Connection? = null,

    /**
     * Information about the videos related to this category.
     */
    val videos: Connection? = null

)
