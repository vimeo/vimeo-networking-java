@file:JvmName("LiveQuotaUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal
import com.vimeo.networking2.enums.LiveQuotaStatusType
import com.vimeo.networking2.enums.asEnum

/**
 * Live Quota information.
 *
 * @param streams Live streams quota data.
 * @param time Live time data.
 * @param status Live status data.
 */
@Internal
@JsonClass(generateAdapter = true)
data class LiveQuota(

    @Internal
    @Json(name = "streams")
    val streams: LiveStreamsQuota? = null,

    @Internal
    @Json(name = "time")
    val time: LiveTime? = null,

    @Internal
    @Json(name = "status")
    val status: String? = null

)

/**
 * @see LiveQuota.status
 * @see LiveQuotaStatusType
 */
val LiveQuota.statusType: LiveQuotaStatusType
    get() = status.asEnum(LiveQuotaStatusType.UNKNOWN)
