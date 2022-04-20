package com.vimeo.networking2.params

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.TeamEntity
import com.vimeo.networking2.enums.TeamEntityType

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
)
