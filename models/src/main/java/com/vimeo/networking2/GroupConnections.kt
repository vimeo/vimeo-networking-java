package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * All connections for groups.
 *
 * @param users Information about the members or moderators of this group.
 * @param videos Information about the videos contained within this group.
 */
@JsonClass(generateAdapter = true)
data class GroupConnections(

    @Json(name = "users")
    val users: BasicConnection? = null,

    @Json(name = "videos")
    val videos: BasicConnection? = null

)
