package com.vimeo.networking2

import com.vimeo.networking2.enums.LiveQuotaStatus

/**
 * Based on CAPABILITY_LIVE_EVENT.
 */
data class LiveQuota(

    /**
     * The status code for the user's ability to live stream.
     */
    val status: LiveQuotaStatus?,

    /**
     * Live streams quota data.
     */
    val streams: LiveStreamsQuota?,

    /**
     * Live time data.
     */
    val time: LiveTime?

)
