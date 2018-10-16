package com.vimeo.networking2

/**
 * Live stats data.
 */
data class LiveStats(

    /**
     * The current total amount of plays this video has received.
     */
    val plays: Long? = null,

    /**
     * The total amount of time spent watching this video by all viewers.
     */
    val totalViewTime: Long? = null,

    /**
     * Information about the number of people watching the stream.
     */
    val viewers: LiveStatsViewers? = null

)
