@file:JvmName("FolderConnectionsUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * All of the connections for a team membership.
 *
 * @param owner A connection object indicating how to get the owner of this user.
 */
@JsonClass(generateAdapter = true)
data class TeamMembershipConnections(

    @Json(name = "owner")
    val owner: TeamOwnerConnection? = null
)
