@file:JvmName("SubscriptionInteractionUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.StreamType
import com.vimeo.networking2.enums.asEnum
import java.util.*

/**
 * Information on the subscription video action.
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
    val stream: String? = null

)

/**
 * @see SubscriptionInteraction.stream
 */
val SubscriptionInteraction.streamType: StreamType
    get() = stream.asEnum(StreamType.UNKNOWN)


