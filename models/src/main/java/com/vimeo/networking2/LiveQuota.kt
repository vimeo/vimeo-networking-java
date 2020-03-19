@file:JvmName("LiveQuotaUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal
import com.vimeo.networking2.enums.LiveQuotaStatusType
import com.vimeo.networking2.enums.asEnum
import java.io.Serializable

/**
 * Live Quota information.
 */
@Internal
@JsonClass(generateAdapter = true)
data class LiveQuota(
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
    val time: LiveTime? = null,

    /**
     * Live status data.
     */
    @Internal
    @Json(name = "status")
    val status: String? = null

): Serializable {

    companion object {
        private const val serialVersionUID = -617738141L
    }
}

/**
 * @see LiveQuota.status
 * @see LiveQuotaStatusType
 */
val LiveQuota.statusType: LiveQuotaStatusType
    get() = status.asEnum(LiveQuotaStatusType.UNKNOWN)
