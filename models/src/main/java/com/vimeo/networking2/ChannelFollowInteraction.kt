package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.FollowType
import java.util.*

/**
 * Follow a channel interaction.
 */
@JsonClass(generateAdapter = true)
data class ChannelFollowInteraction(

    /**
     * Whether the authenticated user has followed this channel.
     * This data requires a bearer token with the private scope.
     */
    @Json(name = "added")
    val added: Boolean? = null,

    /**
     * The time in ISO 8601 format that the user followed this channel, or the null value
     * if the user hasn't followed the channel. This data requires a bearer token with the
     * private scope.
     */
    @Json(name = "added_time")
    val addedTime: Date? = null,

    /**
     * Whether the authenticated user is a moderator or subscriber. This data requires a
     * bearer token with the private scope.
     */
    @Json(name = "type")
    val type: FollowType? = null,

    /**
     * The URI for following or unfollowing this channel. PUT to this URI to follow the channel,
     * or DELETE to this URI to unfollow the channel. This data requires a bearer token with
     * the private scope.
     */
    @Json(name = "uri")
    val uri: String? = null

)
