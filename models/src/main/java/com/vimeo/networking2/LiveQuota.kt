package com.vimeo.networking2

import com.vimeo.networking2.enums.LiveQuotaStatus

/**
 * Requires [CapabilitiesType.CAPABILITY_LIVE_EVENT].
 */
data class LiveQuota(

    /**
     * The status code for the user's ability to live stream.
     */
    val status: LiveQuotaStatus? = null,

    /**
     * Live streams quota data.
     */
    val streams: LiveStreamsQuota? = null,

    /**
     * Live time data.
     */
    val time: LiveTime? = null

)
