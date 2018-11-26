@file:JvmName("LiveQuotaUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal

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
    val time: LiveTime? = null

)
