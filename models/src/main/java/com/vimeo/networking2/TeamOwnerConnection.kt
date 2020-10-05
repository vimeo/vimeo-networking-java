package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.Connection

/**
 * Connection data.
 */
@JsonClass(generateAdapter = true)
data class TeamOwnerConnection(

    @Json(name = "options")
    override val options: List<String>? = null,

    @Json(name = "uri")
    override val uri: String? = null,

    /**
     * The team owner's display name.
     */
    @Json(name = "display_name")
    val displayName: String? = null,

    /**
     * The total number of team member invites remaining.
     */
    @Json(name = "invites_remaining")
    val invitesRemaining: Int? = null,

    /**
     * The total number of owners on this connection.
     */
    @Json(name = "total")
    val total: Int? = null

) : Connection
