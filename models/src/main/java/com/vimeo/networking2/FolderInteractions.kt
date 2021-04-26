package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * The interactions for a folder.
 *
 * @param addSubfolder The interaction used to add a subfolder as well as determine capability for adding subfolders.
 */
@JsonClass(generateAdapter = true)
data class FolderInteractions(
    @Json(name = "add_subfolder")
    val addSubfolder: AddSubfolderInteraction? = null
)
