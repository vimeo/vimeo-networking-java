package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * The permission policy for a team member in a folder.
 *
 * @param name The name of the permission level the user has.
 * @param permissionActions The actions the user can take.
 */
@JsonClass(generateAdapter = true)
data class PermissionPolicy(

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "permission_actions")
    val permissionActions: PermissionActions? = null
)
