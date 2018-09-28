package com.vimeo.networking2

import com.vimeo.networking2.enums.LiveQuotaStatus

data class LiveQuota(

    /**
     * The status code for the user's ability to live stream.
     */
    val status: LiveQuotaStatus?,

    val streams: LiveStreamsQuota?,

    val time: LiveTime?

)
