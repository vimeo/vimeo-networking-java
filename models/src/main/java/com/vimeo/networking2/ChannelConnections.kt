package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * All connections for a channel.
 *
 * @param privacyUsers Information provided to channel moderators about which users they have specifically permitted to
 * access a private channel. This data requires a bearer token with the private scope.
 * @param users Information about the users following or moderating this channel.
 * @param videos Information about the videos that belong to this channel.
 */
@JsonClass(generateAdapter = true)
data class ChannelConnections(

    @Json(name = "privacy_users")
    val privacyUsers: BasicConnection? = null,

    @Json(name = "users")
    val users: BasicConnection? = null,

    @Json(name = "videos")
    val videos: BasicConnection? = null

)
