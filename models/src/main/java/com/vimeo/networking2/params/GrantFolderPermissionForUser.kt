package com.vimeo.networking2.params

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 *  Represents a User URI being used to grant permission for a particular folder.
 *
 *  @param uri The user URI that will be added to the given folder.
 */
@JsonClass(generateAdapter = true)
data class GrantFolderPermissionForUser(
    @Json(name = "uri")
    val uri: String
)
