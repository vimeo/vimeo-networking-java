package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * The access granted to a team member in a folder.
 *
 * @param folder The folder with the largest scope to which the user has been granted access.
 * @param permissionPolicy The permissions that have been
 */
@JsonClass(generateAdapter = true)
data class AccessGrant(
    @Json(name = "folder")
    val folder: Folder? = null,

    @Json(name = "permission_policy")
    val permissionPolicy: PermissionPolicy? = null
)
