package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * The actions that can be taken by a team member in a folder.
 *
 * @param folderDeleteVideo True if the user can delete videos from the folder, false otherwise.
 * @param folderEdit True if the user can edit the folder, false otherwise.
 * @param folderInvite True if the user can invite others to the folder, false otherwise.
 * @param folderView True if the user can view the folder, false otherwise.
 * @param folderAddSubFolders True if the user can add a subfolder, false otherwise.
 */
@JsonClass(generateAdapter = true)
data class PermissionActions(

    @Json(name = "folder.delete_video")
    val folderDeleteVideo: Boolean? = null,

    @Json(name = "folder.edit")
    val folderEdit: Boolean? = null,

    @Json(name = "folder.invite")
    val folderInvite: Boolean? = null,

    @Json(name = "folder.view")
    val folderView: Boolean? = null,

    @Json(name = "folder.add_subfolders")
    val folderAddSubFolders: Boolean? = null

)
