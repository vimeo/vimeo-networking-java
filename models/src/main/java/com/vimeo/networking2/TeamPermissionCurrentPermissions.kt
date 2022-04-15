package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Represents the current permission for a [TeamPermission].
 *
 * @param permissionPolicyUri The uri for the permission
 */
@JsonClass(generateAdapter = true)
data class TeamPermissionCurrentPermissions(
    @Json(name = "permission_policy_uri")
    val permissionPolicyUri: String? = null
)
