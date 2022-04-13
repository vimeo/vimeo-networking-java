package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TeamEntity(
    @Json(name = "type")
    val type: String? = null,

    @Json(name = "uri")
    val uri: String? = null,

    @Json(name = "team_user")
    val teamUser: TeamMembership? = null,

    @Json(name = "team_group")
    val teamGroup: TeamGroup? = null,

    @Json(name = "owner")
    val owner: User? = null,

    @Json(name = "display_options")
    val displayOptions: TeamEntityDisplayOptions? = null
)