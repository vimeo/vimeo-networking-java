package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.Connection

/**
 * The connection for the user's team and members of that team.
 *
 * @param invitesRemaining The number of invites remaining to add new members to a team. Equal to [maxSeats] minus
 * [total].
 * @param maxSeats The total number of seats that can exist on the team. Equal to [invitesRemaining] plus [total].
 * @param total The total number of team members on this connection.
 */
@JsonClass(generateAdapter = true)
data class TeamMembersConnection(

    @Json(name = "invites_remaining")
    val invitesRemaining: Int? = null,

    @Json(name = "max_seats")
    val maxSeats: Int? = null,

    @Json(name = "options")
    override val options: List<String>? = null,

    @Json(name = "uri")
    override val uri: String? = null,

    @Json(name = "total")
    val total: Int? = null

) : Connection
