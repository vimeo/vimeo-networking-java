package com.vimeo.networking2

data class LiveStatsViewers(

    /**
     * The current amount of people watching this video.
     */
    val current: Long?,

    /**
     * The peak amount of people watching this video at any time in the provided date range.
     */
    val peak: Long?
)
