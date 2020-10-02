package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Stores information related to shared access to resources across user accounts.
 */
@JsonClass(generateAdapter = true)
data class Team(

    /**
     * The current number of team members.
     */
    @Json(name = "current_team_size")
    val currentTeamSize: Int? = null,

    /**
     * The maximum number of team members.
     */
    @Json(name = "max_team_size")
    val maximumTeamSize: Int? = null,

    /**
     * Customized information about the team, including the team name, logo image, and accent color.
     */
    @Json(name = "team_membership")
    val teamMembership: TeamMembership? = null,

    /**
     * The owner of the team.
     */
    @Json(name = "owner")
    val owner: User? = null,

    /**
     * Whether or not the team has content shared with any team members yet.
     */
    @Json(name = "has_content_shared")
    val hasContentShared: Boolean? = null,

    /**
     * Customized information about the team, including the team name, logo image, and accent color.
     */
    @Json(name = "team_data")
    val teamBranding: TeamBranding? = null

)
