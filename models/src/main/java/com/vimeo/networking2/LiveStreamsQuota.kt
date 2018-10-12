package com.vimeo.networking2
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LiveStreamsQuota(

    /**
     * The maximum amount of streams that the user can create.
     *
     * Requires [CapabilitiesType.CAPABILITY_LIVE_EVENT].
     */
    @Json(name = "maximum")
    val maximum: Int? = null,

    /**
     * The amount of remaining live streams that the user can create this month.
     *
     * Requires [CapabilitiesType.CAPABILITY_LIVE_EVENT].
     */
    @Json(name = "remaining")
    val remaining: Int? = null


)
