package com.vimeo.networking2

/**
 * All connections for groups.
 */
data class GroupConnections(

    /**
     * Information about the members or moderators of this group.
     */
    val users: Connection? = null,

    /**
     * Information about the videos contained within this group.
     */
    val videos: Connection? = null

)
