@file:JvmName("FolderConnectionsUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * All of the connections for a team membership.
 */
@JsonClass(generateAdapter = true)
data class TeamMembershipConnections(

    /**
     * A connection object indicating how to get the owner of this user.
     */
    @Json(name = "owner")
    val owner: TeamOwnerConnection? = null
)
