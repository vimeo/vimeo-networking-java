package com.vimeo.networking2

/**
 * All connections for a channel.
 */
data class ChannelConnections(

    /**
     * Information provided to channel moderators about which users they have specifically
     * permitted to access a private channel. This data requires a bearer token with the
     * private scope.
     */
    val privacyUsers: Connection? = null,

    /**
     * Information about the users following or moderating this channel.
     */
    val users: Connection? = null,

    /**
     * Information about the videos that belong to this channel.
     */
    val videos: Connection? = null

)
