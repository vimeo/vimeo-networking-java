package com.vimeo.networking2

data class LiveStats(

    /**
     * The current total amount of plays this video has received.
     */
    val plays: Long?,

    /**
     * The total amount of time spent watching this video by all viewers.
     */
    val totalViewTime: Long?,

    /**
     * Information about the number of people watching the stream.
     */
    val viewers: LiveStatsViewers?

)
