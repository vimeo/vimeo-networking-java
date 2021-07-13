@file:JvmName("SubscriptionInteractionUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal
import com.vimeo.networking2.enums.StreamAccessType
import com.vimeo.networking2.enums.asEnum
import java.util.Date

/**
 * Information on the subscription video action.
 *
 * @param drm Whether the video has DRM.
 * @param expiresTime The time in ISO 8601 format when the subscription expires.
 * @param purchaseTime The time in ISO 8601 format when the subscription was purchased.
 * @param streamAccess The stream type. See [SubscriptionInteraction.streamAccessType].
 */
@Internal
@JsonClass(generateAdapter = true)
data class SubscriptionInteraction(

    @Internal
    @Json(name = "drm")
    val drm: Boolean? = null,

    @Internal
    @Json(name = "expires_time")
    val expiresTime: Date? = null,

    @Internal
    @Json(name = "purchase_time")
    val purchaseTime: Date? = null,

    @Internal
    @Json(name = "stream")
    val streamAccess: String? = null

)

/**
 * @see SubscriptionInteraction.streamAccess
 * @see StreamAccessType
 */
val SubscriptionInteraction.streamAccessType: StreamAccessType
    get() = streamAccess.asEnum(StreamAccessType.UNKNOWN)
