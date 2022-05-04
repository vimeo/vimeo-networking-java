package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.Entity

/**
 * Represents the current permission relationship a [teamEntity] has with a resource.
 *
 * @param teamEntity The entity which this class expresses a permission relationship towards for the resource passed in
 * to the call
 * @param applicablePermissionPolicies set of potential permissions the [teamEntity] can have its permission
 * relationship to a the resource changed to
 * @param currentPermissions This class expresses any current way the [TeamEntity] is associated with the resource
 * through this.
 * @param metadata Any way that the current authenticated user can interact with this [TeamEntity] in
 * respect to permission changes, is expressed within the interactions located within this.
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
) : Entity {
    override val identifier: String? = teamEntity?.identifier
}

/**
 * Get the current [PermissionPolicy] if possible; This search is predicated upon
 * [TeamPermission.applicablePermissionPolicies] containing an item with the same uri as
 * [TeamPermission.currentPermissions], [TeamPermissionCurrentPermissions.permissionPolicyUri].
 */
val TeamPermission.currentPermissionPolicy: PermissionPolicy? get() =
    if (currentPermissions?.permissionPolicyUri == null) {
        null
    } else {
        applicablePermissionPolicies?.firstOrNull { it.uri == currentPermissions.permissionPolicyUri }
    }
