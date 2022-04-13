package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TeamPermission(
    @Json(name = "team_entity")
    val teamEntity: TeamEntity? = null,
    @Json(name = "applicable_permission_policies")
    val applicablePermissionPolicies: List<PermissionPolicy>? = null,
    @Json(name = "current_permissions")
    val currentPermissions: TeamPermissionCurrentPermissions? = null
)

@JsonClass(generateAdapter = true)
data class TeamPermissionCurrentPermissions(
    @Json(name = "permission_policy_uri")
    val permissionPolicyUri: String? = null
)