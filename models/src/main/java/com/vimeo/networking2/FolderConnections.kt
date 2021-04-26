@file:JvmName("FolderConnectionsUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * All of the connections for a folder.
 *
 * @param folders A basic connection object indicating how to return all the sub-folders in the folder.
 * @param items A basic connection object indicating how to return all the project items in the folder.
 * @param teamMembers Information about the authenticated user's team.
 * @param parentFolder Information about the folder's parent folder if there is one.
 * @param videos A basic connection object indicating how to return all the videos in the folder.
 */
@JsonClass(generateAdapter = true)
data class FolderConnections(

    @Json(name = "folders")
    val folders: BasicConnection? = null,

    @Json(name = "items")
    val items: BasicConnection? = null,

    @Json(name = "team_members")
    val teamMembers: BasicConnection? = null,

    @Json(name = "parent_folder")
    val parentFolder: DefaultConnection? = null,

    @Json(name = "videos")
    val videos: BasicConnection? = null
)
