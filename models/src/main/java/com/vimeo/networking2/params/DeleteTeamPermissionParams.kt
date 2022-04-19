package com.vimeo.networking2.params

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.TeamEntity
import com.vimeo.networking2.enums.TeamEntityType
import com.vimeo.networking2.type

/**
 * Body for the request which removes a permission policy association with a resource for a specific [TeamEntity].
 *
 * @property teamEntityType the type of a [TeamEntity]
 * @property teamEntityUri the uri of a specific [TeamEntity]
 */
@JsonClass(generateAdapter = true)
data class DeleteTeamPermissionParams(
    @Json(name = "team_entity_type")
    val teamEntityType: TeamEntityType? = null,

    @Json(name = "team_entity_uri")
    val teamEntityUri: String? = null
) {
    companion object {
        /**
         * Convenience method which parses entities for relevant params.
         *
         * @param teamEntity the entity we want to parse for purposes of deleting some permission policy association
         * @return am instance of [DeleteTeamPermissionParams] as constructed from a [teamEntity]
         */
        fun fromEntities(teamEntity: TeamEntity) = DeleteTeamPermissionParams(
            teamEntityType = teamEntity.type,
            teamEntityUri = teamEntity.uri
        )
    }
}
