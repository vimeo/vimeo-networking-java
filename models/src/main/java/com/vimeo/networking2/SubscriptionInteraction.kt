package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal
import com.vimeo.networking2.enums.StreamType
import java.util.*

/**
 * Information on the subscription video action.
 */
@Internal
@JsonClass(generateAdapter = true)
data class SubscriptionInteraction(

    /**
     * Whether the video has DRM.
     */
    @Internal
    @Json(name = "drm")
    val drm: Boolean? = null,

    /**
     * The time in ISO 8601 format when the subscription expires.
     */
    @Internal
    @Json(name = "expires_time")
    val expiresTime: Date? = null,

    /**
     * The time in ISO 8601 format when the subscription was purchased.
     */
    @Internal
    @Json(name = "purchase_time")
    val purchaseTime: Date? = null,

    /**
     * The stream type.
     */
    @Internal
    @Json(name = "stream")
    val streamType: StreamType? = null

)
