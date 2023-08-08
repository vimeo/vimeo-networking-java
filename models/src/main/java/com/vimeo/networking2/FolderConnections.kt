@file:JvmName("FolderConnectionsUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * All of the connections for a folder.
 *
 * @param ancestorPath An ordered list of connections to the parent folders of a folder. The zeroth index in the list
 * will be the immediate parent of the folder.
 * @param folders A basic connection object indicating how to return all the sub-folders in the folder.
 * @param items A basic connection object indicating how to return all the project items in the folder.
 * @param teamMembers Information about the authenticated user's team.
 * @param parentFolder Information about the folder's parent folder if there is one.
 * @param videos A basic connection object indicating how to return all the videos in the folder.
 * @param teamPermissions A basic connection to team permissions associated with this folder resource
 * @param personalTeamFolderOwner Information about the owner of the personal team folder.
 */
@JsonClass(generateAdapter = true)
data class FolderConnections(

    @Json(name = "ancestor_path")
    val ancestorPath: List<FolderAncestorConnection>? = null,

    @Json(name = "folders")
    val folders: BasicConnection? = null,

    @Json(name = "items")
    val items: BasicConnection? = null,

    @Json(name = "team_members")
    val teamMembers: BasicConnection? = null,

    @Json(name = "parent_folder")
    val parentFolder: DefaultConnection? = null,

    @Json(name = "videos")
    val videos: BasicConnection? = null,

    @Json(name = "team_permissions")
    val teamPermissions: BasicConnection? = null,

    @Json(name = "personal_team_folder_owner")
    val personalTeamFolderOwner: BasicConnection? = null
)
