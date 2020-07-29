@file:JvmName("FolderConnectionsUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * All of the connections for a folder.
 */
@JsonClass(generateAdapter = true)
data class FolderConnections(

    /**
     * A basic connection object indicating how to return all the sub-folders in the folder.
     */
    @Json(name = "folders")
    val folders: Connection? = null,

    /**
     * A basic connection object indicating how to return all the project items in the folder.
     */
    @Json(name = "items")
    val items: Connection? = null,

    /**
     * Information about the authenticated user's team.
     */
    @Json(name = "team_members")
    val teamMembers: Connection? = null,

    /**
     * A basic connection object indicating how to return all the videos in the folder.
     */
    @Json(name = "videos")
    val videos: Connection? = null
)
