package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Live Stream Quota dto.
 *
 * Requires [CapabilitiesType.CAPABILITY_LIVE_EVENT].
 */
@JsonClass(generateAdapter = true)
data class LiveStreamsQuota(

    /**
     * The maximum amount of streams that the user can create.
     */
    @Json(name = "maximum")
    val maximum: Int? = null,

    /**
     * The amount of remaining live streams that the user can create this month.
     */
    @Json(name = "remaining")
    val remaining: Int? = null

)
