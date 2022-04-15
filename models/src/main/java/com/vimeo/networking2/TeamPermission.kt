package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Represents the current permission relationship a [teamEntity] has with a resource. This class expresses any current
 * way the [TeamEntity] is associated with the resource through [currentPermissions].  This class expresses any way the
 * [TeamEntity] can have it's permission relationship to a resource edited via the set of potential permissions listed
 * in [applicablePermissionPolicies]. Any way that the current authenticated user can interact with this [TeamEntity] in
 * respect to permission changes, is expressed within the interactions located within [metadata]
 */
@JsonClass(generateAdapter = true)
data class TeamPermission(
    @Json(name = "team_entity")
    val teamEntity: TeamEntity? = null,

    @Json(name = "applicable_permission_policies")
    val applicablePermissionPolicies: List<PermissionPolicy>? = null,

    @Json(name = "current_permissions")
    val currentPermissions: TeamPermissionCurrentPermissions? = null,

    @Json(name = "metadata")
    val metadata: MetadataInteractions<TeamPermissionInteraction>? = null
)
