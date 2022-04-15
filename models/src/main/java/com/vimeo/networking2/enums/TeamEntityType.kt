package com.vimeo.networking2.enums

/**
 * An enumeration of [com.vimeo.networking2.TeamEntity.rawType].
 */
enum class TeamEntityType(override val value: String?) : StringValue {
    ALL_TEAM("all_team"),
    TEAM_USER("team_user"),
    TEAM_GROUP("team_group"),
    UNKNOWN(null)
}
