package com.vimeo.networking2

import com.vimeo.networking2.enums.StreamType
import com.vimeo.networking2.enums.StreamType.UNKNOWN
import java.util.*

/**
 * Information on the subscribe a video action.
 */
data class Subscription(

    /**
     * Whether the video has DRM.
     */
    val drm: Boolean? = null,

    /**
     * The time in ISO 8601 format when the subscription expires.
     */
    val expiresTime: Date? = null,

    /**
     * The tine in ISO 8601 format when the subscription was purchased.
     */
    val purchaseTime: Date? = null,

    /**
     * The stream type.
     */
    val streamType: StreamType = UNKNOWN

)
