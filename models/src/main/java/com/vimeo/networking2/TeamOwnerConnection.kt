package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.Connection

/**
 * Connection data.
 *
 * @param displayName The team owner's display name.
 * @param invitesRemaining The total number of team member invites remaining.
 * @param total The total number of owners on this connection.
 */
@JsonClass(generateAdapter = true)
data class TeamOwnerConnection(

    @Json(name = "options")
    override val options: List<String>? = null,

    @Json(name = "uri")
    override val uri: String? = null,

    @Json(name = "display_name")
    val displayName: String? = null,

    @Json(name = "invites_remaining")
    val invitesRemaining: Int? = null,

    @Json(name = "total")
    val total: Int? = null

) : Connection
