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
 */
@JsonClass(generateAdapter = true)
data class TeamMembership(

    /**
     * The URI to independently request this team membership information.
     */
    @Json(name = "uri")
    val uri: String? = null,

    /**
     * The user's role on the team.
     * @see TeamMembership.roleType
     */
    @Json(name = "permission_level")
    val role: String? = null,

    /**
     * A localized string for display purposes that names the user's role on the team.
     * @see role
     */
    @Json(name = "role")
    val localizedRole: String? = null,

    /**
     * The URL for the invited user to join the team.
     * The value of this field is null if the invited user has already joined.
     * (e.g. https://vimeo.com/user/seat?code=e7c71ae7f4dc5d71a3bceb4d1d9e)
     */
    @Json(name = "invite_url")
    val inviteUrl: String? = null,

    /**
     * The team member's email.
     */
    @Json(name = "email")
    val email: String? = null,

    /**
     * The team member. The value of this field is null if the user hasn't joined the team yet.
     */
    @Json(name = "user")
    val user: User? = null,

    /**
     * The time in ISO 8601 format at which the invite was sent.
     */
    @Json(name = "created_time")
    val createdTime: Date? = null,

    /**
     * The time in ISO 8601 format at which the team membership was last modified.
     */
    @Json(name = "modified_time")
    val modifiedTime: Date? = null,

    /**
     * The time in ISO 8601 format at which the invite was accepted.
     */
    @Json(name = "joined_time")
    val joinedTime: Date? = null,

    /**
     * The status of the user's team membership.
     * @see TeamMembership.teamInviteStatusType
     */
    @Json(name = "status")
    val teamInviteStatus: String? = null,

    /**
     * The resource key that identifies team membership (this is not unique per membership instance, only unique per
     * team).
     */
    @Json(name = "resource_key")
    val resourceKey: String? = null,

    /**
     * Whether the team member has folder access.
     */
    @Json(name = "has_folder_access")
    val hasFolderAccess: Boolean? = null,

    /**
     * The team membership metadata.
     */
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
