package com.vimeo.networking2.enums

/**
 * An enumeration of [com.vimeo.networking2.TeamEntity.rawType].
 */
enum class TeamEntityType(override val value: String?) : StringValue {
    /**
     * Team entity representing all users in a team.
     */
    ALL_TEAM("all_team"),

    /**
     * Team entity respresenting a single user on a team.
     */
    TEAM_USER("team_user"),

    /**
     * Team entity representing a subset of users on a team.
     */
    TEAM_GROUP("team_group"),

    /**
     * Unrecognized team entity type.
     */
    UNKNOWN(null)
}
