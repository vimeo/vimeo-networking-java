package com.vimeo.networking2

data class CategoryConnections(

    /**
     * Information about the channels related to this category.
     */
    val channels: Connection?,

    /**
     * Information about the groups related to this category.
     */
    val groups: Connection?,

    /**
     * Information about the users related to this category.
     */
    val users: Connection?,

    /**
     * Information about the videos related to this category.
     */
    val videos: Connection?

)
