package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.LiveQuotaStatus
import com.vimeo.networking2.enums.LiveQuotaStatus.UNKNOWN

/**
 * Requires [CapabilitiesType.CAPABILITY_LIVE_EVENT].
 */
@JsonClass(generateAdapter = true)
data class LiveQuota(

    /**
     * The status code for the user's ability to live stream.
     */
    @Json(name = "status")
    val status: LiveQuotaStatus = UNKNOWN,

    /**
     * Live streams quota data.
     */
    @Json(name = "streams")
    val streams: LiveStreamsQuota? = null,

    /**
     * Live time data.
     */
    @Json(name = "time")
    val time: LiveTime? = null

)
