package com.vimeo.networking2

data class LiveStreamsQuota(

    /**
     * The maximum amount of streams that the user can create.
     *
     * Based on CAPABILITY_LIVE_EVENT.
     */
    val maximum: Int?,

    /**
     * The amount of remaining live streams that the user can create this month.
     *
     * Based on CAPABILITY_LIVE_EVENT.
     */
    val remaining: Int?


)
