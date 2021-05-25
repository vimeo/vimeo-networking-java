package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.Entity
import com.vimeo.networking2.enums.TeamInviteStatusType
import com.vimeo.networking2.enums.TeamRoleType
import com.vimeo.networking2.enums.asEnum
import java.util.Date

/**
 * Stores information related to relevant members of a team.
 *
 * @param accessGrant The grant that gives the team member access to the current context.
 * @param uri The URI to independently request this team membership information.
 * @param role The user's role on the team. See [TeamMembership.roleType].
 * @param localizedRole A localized string for display purposes that names the user's role on the team. See [role].
 * @param inviteUrl The URL for the invited user to join the team. The value of this field is null if the invited user
 * has already joined. (e.g. https://vimeo.com/user/seat?code=e7c71ae7f4dc5d71a3bceb4d1d9e)
 * @param email The team member's email.
 * @param user The team member. The value of this field is null if the user hasn't joined the team yet.
 * @param createdTime The time in ISO 8601 format at which the invite was sent.
 * @param modifiedTime The time in ISO 8601 format at which the team membership was last modified.
 * @param joinedTime The time in ISO 8601 format at which the invite was accepted.
 * @param teamInviteStatus The status of the user's team membership. See [TeamMembership.teamInviteStatusType].
 * @param resourceKey The resource key that identifies team membership (this is not unique per membership instance, only
 * unique per team).
 * @param hasFolderAccess Whether the team member has folder access.
 * @param metadata The team membership metadata.
 */
@JsonClass(generateAdapter = true)
data class TeamMembership(

    @Json(name = "access_grant")
    val accessGrant: AccessGrant? = null,

    @Json(name = "uri")
    val uri: String? = null,

    @Json(name = "permission_level")
    val role: String? = null,

    @Json(name = "role")
    val localizedRole: String? = null,

    @Json(name = "invite_url")
    val inviteUrl: String? = null,

    @Json(name = "email")
    val email: String? = null,

    @Json(name = "user")
    val user: User? = null,

    @Json(name = "created_time")
    val createdTime: Date? = null,

    @Json(name = "modified_time")
    val modifiedTime: Date? = null,

    @Json(name = "joined_time")
    val joinedTime: Date? = null,

    @Json(name = "status")
    val teamInviteStatus: String? = null,

    @Json(name = "resource_key")
    val resourceKey: String? = null,

    @Json(name = "has_folder_access")
    val hasFolderAccess: Boolean? = null,

    @Json(name = "metadata")
    val metadata: Metadata<TeamMembershipConnections, BasicInteraction>? = null

) : Entity {
    override val identifier: String? = uri
}

/**
 * @see TeamMembership.role
 * @see TeamRoleType
 */
val TeamMembership.roleType: TeamRoleType
    get() = role.asEnum(TeamRoleType.UNKNOWN)

/**
 * @see TeamMembership.teamInviteStatus
 * @see TeamInviteStatusType
 */
val TeamMembership.teamInviteStatusType: TeamInviteStatusType
    get() = teamInviteStatus.asEnum(TeamInviteStatusType.UNKNOWN)
