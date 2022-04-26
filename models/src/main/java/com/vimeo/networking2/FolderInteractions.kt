package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * The interactions for a folder.
 *
 * @param addRemoveVideos The interaction used to determine if the user can add to or remove videos from the folder.
 * @param addSubfolder The interaction used to add a subfolder as well as determine capability for adding subfolders.
 * @param delete The interaction used to determine if the user can delete the folder.
 * @param deleteVideo The interaction that shows whether the user can delete videos from the folder.
 * @param editSettings The interaction that shows whether the user can edit the folder's settings.
 * @param invite The interaction that shows whether the user can invite other users to manage the folder.
 */
@JsonClass(generateAdapter = true)
data class FolderInteractions(

    @Json(name = "edit")
    val addRemoveVideos: BasicInteraction? = null,

    @Json(name = "add_subfolder")
    val addSubfolder: AddSubfolderInteraction? = null,

    @Json(name = "delete")
    val delete: BasicInteraction? = null,

    @Json(name = "delete_video")
    val deleteVideo: BasicInteraction? = null,

    @Json(name = "edit_settings")
    val editSettings: BasicInteraction? = null,

    @Json(name = "invite")
    val invite: BasicInteraction? = null,
)
