package com.vimeo.networking2

data class LiveStreamsQuota(

    /**
     * The maximum amount of streams that the user can create.
     *
     * Requires [CapabilitiesType.CAPABILITY_LIVE_EVENT].
     */
    val maximum: Int? = null,

    /**
     * The amount of remaining live streams that the user can create this month.
     *
     * Requires [CapabilitiesType.CAPABILITY_LIVE_EVENT].
     */
    val remaining: Int? = null


)
