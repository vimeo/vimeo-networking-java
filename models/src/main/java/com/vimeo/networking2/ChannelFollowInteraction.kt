package com.vimeo.networking2

import com.vimeo.networking2.enums.FollowType
import java.util.*

data class ChannelFollowInteraction(

    /**
     * Whether the authenticated user has followed this channel.
     * This data requires a bearer token with the private scope.
     */
    val added: Boolean?,

    /**
     * The time in ISO 8601 format that the user followed this channel, or the null value
     * if the user hasn't followed the channel. This data requires a bearer token with the
     * private scope.
     */
    val addedTime: Date?,

    /**
     * Whether the authenticated user is a moderator or subscriber. This data requires a
     * bearer token with the private scope.
     */
    val type: FollowType?,

    /**
     * The URI for following or unfollowing this channel. PUT to this URI to follow the channel,
     * or DELETE to this URI to unfollow the channel. This data requires a bearer token with
     * the private scope.
     */
    val uri: String?

)
