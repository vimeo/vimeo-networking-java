package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * All connections for groups.
 */
@JsonClass(generateAdapter = true)
data class GroupConnections(

    /**
     * Information about the members or moderators of this group.
     */
    @Json(name = "users")
    val users: Connection? = null,

    /**
     * Information about the videos contained within this group.
     */
    @Json(name = "videos")
    val videos: Connection? = null

)
