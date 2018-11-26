package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * All connections for a channel.
 */
@JsonClass(generateAdapter = true)
data class ChannelConnections(

    /**
     * Information provided to channel moderators about which users they have specifically
     * permitted to access a private channel. This data requires a bearer token with the
     * private scope.
     */
    @Json(name = "privacy_users")
    val privacyUsers: Connection? = null,

    /**
     * Information about the users following or moderating this channel.
     */
    @Json(name = "users")
    val users: Connection? = null,

    /**
     * Information about the videos that belong to this channel.
     */
    @Json(name = "videos")
    val videos: Connection? = null

)
