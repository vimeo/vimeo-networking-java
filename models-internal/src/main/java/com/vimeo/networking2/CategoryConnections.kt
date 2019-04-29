package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * All connections for a category.
 */
@JsonClass(generateAdapter = true)
data class CategoryConnections(

    /**
     * Information about the channels related to this category.
     */
    @Json(name = "channels")
    val channels: Connection? = null,

    /**
     * Information about the groups related to this category.
     */
    @Json(name = "groups")
    val groups: Connection? = null,

    /**
     * Information about the users related to this category.
     */
    @Json(name = "users")
    val users: Connection? = null,

    /**
     * Information about the videos related to this category.
     */
    @Json(name = "videos")
    val videos: Connection? = null

)
