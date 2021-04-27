package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * The connection to a parent folder in the ancestor hierarchy of a folder.
 *
 * @param uri The URI of the parent folder.
 * @param name The name of the parent folder.
 */
@JsonClass(generateAdapter = true)
data class FolderAncestorConnection(

    @Json(name = "uri")
    val uri: String? = null,

    @Json(name = "name")
    val name: String? = null
)
