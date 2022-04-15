package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.Date

/**
 * Represents a team entity which is group of users.
 *
 * @param uri the uri for the team group
 * @param ownerId an Id for the team owner
 * @param name the name of the team group
 * @param createdOn the date the team group was created
 * @param modifiedOn the date the team group was last modified
 * @param metadata the metadata containing any connections for the team group
 */
@JsonClass(generateAdapter = true)
data class TeamGroup(
    @Json(name = "uri")
    val uri: String? = null,

    @Json(name = "owner_id")
    val ownerId: String? = null,

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "created_on")
    val createdOn: Date? = null,

    @Json(name = "modified_on")
    val modifiedOn: Date? = null,

    @Json(name = "metadata")
    val metadata: MetadataConnections<TeamGroupConnections>
)
