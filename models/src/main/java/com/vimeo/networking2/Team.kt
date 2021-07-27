package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.Entity

/**
 * Stores information related to shared access to resources across user accounts.
 *
 * @param currentTeamSize The current number of team members.
 * @param maximumTeamSize The maximum number of team members.
 * @param teamMembership Customized information about the team, including the team name, logo image, and accent color.
 * @param owner The owner of the team.
 * @param userRole A translated name of the logged in user's role on the team.
 * @param hasContentShared Whether or not the team has content shared with any team members yet.
 * @param teamBranding Customized information about the team, including the team name, logo image, and accent color.
 */
@JsonClass(generateAdapter = true)
data class Team(

    @Json(name = "current_team_size")
    val currentTeamSize: Int? = null,

    @Json(name = "max_team_size")
    val maximumTeamSize: Int? = null,

    @Json(name = "team_membership")
    val teamMembership: TeamMembership? = null,

    @Json(name = "owner")
    val owner: User? = null,

    @Json(name = "user_role")
    val userRole: String? = null,

    @Json(name = "has_content_shared")
    val hasContentShared: Boolean? = null,

    @Json(name = "team_data")
    val teamBranding: TeamBranding? = null

) : Entity {
    override val identifier: String? = owner?.identifier
}
