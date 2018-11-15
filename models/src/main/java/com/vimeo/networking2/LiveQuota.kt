@file:JvmName("LiveQuotaUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal
import com.vimeo.networking2.enums.LiveQuotaStatus
import com.vimeo.networking2.enums.asEnum

/**
 * Live Quota information.
 */
@Internal
@JsonClass(generateAdapter = true)
data class LiveQuota(

    /**
     * The status code for the user's ability to live stream.
     * @see LiveQuota.liveQuotaStatus
     */
    @Internal
    @Json(name = "status")
    val status: String? = null,

    /**
     * Live streams quota data.
     */
    @Internal
    @Json(name = "streams")
    val streams: LiveStreamsQuota? = null,

    /**
     * Live time data.
     */
    @Internal
    @Json(name = "time")
    val time: LiveTime? = null

)

/**
 * @see LiveQuota.status
 * @see LiveQuotaStatus
 */
val LiveQuota.liveQuotaStatus: LiveQuotaStatus
    get() = status.asEnum(LiveQuotaStatus.UNKNOWN)
