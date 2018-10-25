package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.StreamType
import java.util.*

/**
 * Information on the subscription a video action.
 */
@JsonClass(generateAdapter = true)
data class SubscriptionInteraction(

    /**
     * Whether the video has DRM.
     */
    @Json(name = "drm")
    val drm: Boolean? = null,

    /**
     * The time in ISO 8601 format when the subscription expires.
     */
    @Json(name = "expires_time")
    val expiresTime: Date? = null,

    /**
     * The time in ISO 8601 format when the subscription was purchased.
     */
    @Json(name = "purchase_time")
    val purchaseTime: Date? = null,

    /**
     * The stream type.
     */
    @Json(name = "stream")
    val streamType: StreamType? = null

)
