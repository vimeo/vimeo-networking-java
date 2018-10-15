package com.vimeo.networking2

/**
 * Live Stream Quota dto.
 *
 * Requires [CapabilitiesType.CAPABILITY_LIVE_EVENT].
 */
data class LiveStreamsQuota(

    /**
     * The maximum amount of streams that the user can create.
     */
    val maximum: Int? = null,

    /**
     * The amount of remaining live streams that the user can create this month.
     */
    val remaining: Int? = null

)
