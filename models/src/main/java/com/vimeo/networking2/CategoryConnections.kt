package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * All connections for a category.
 *
 * @param channels Information about the channels related to this category.
 * @param groups Information about the groups related to this category.
 * @param users Information about the users related to this category.
 * @param videos Information about the videos related to this category.
 */
@JsonClass(generateAdapter = true)
data class CategoryConnections(

    @Json(name = "channels")
    val channels: BasicConnection? = null,

    @Json(name = "groups")
    val groups: BasicConnection? = null,

    @Json(name = "users")
    val users: BasicConnection? = null,

    @Json(name = "videos")
    val videos: BasicConnection? = null

)
