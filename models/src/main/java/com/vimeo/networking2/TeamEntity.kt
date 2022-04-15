package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.TeamEntityType
import com.vimeo.networking2.enums.asEnum

/**
 * Represents a team entity.
 *
 * @param rawType a raw string representation of the types defined by [TeamEntityType]
 * @param uri a uri which returns a singular instance of this [TeamEntity]
 * @param teamUser when the [TeamEntity] is of type [TeamEntityType.TEAM_USER], this will have a value representing the
 * data for that type
 * @param teamGroup when the [TeamEntity] is of type [TeamEntityType.TEAM_GROUP], this will have a value representing
 * the data for that type
 * @param owner when the [TeamEntity] is of type [TeamEntityType.ALL_TEAM], this will have a value representing the
 * owner of the team.
 * @param displayOptions various client display options that are server driven. See class definition for more detail
 */
@JsonClass(generateAdapter = true)
data class TeamEntity(
    @Json(name = "type")
    val rawType: String? = null,

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

/**
 * @see [TeamEntity.rawType]
 * @see TeamEntityType
 */
val TeamEntity.type: TeamEntityType
    get() = rawType.asEnum(TeamEntityType.UNKNOWN)
