package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Represents the connections associated with a [TeamGroup].
 *
 * @param users a connection which returns all users within a [TeamGroup]
 */
@JsonClass(generateAdapter = true)
data class TeamGroupConnections(
    @Json(name = "users")
    val users: BasicConnection
)
