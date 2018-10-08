package com.vimeo.networking2

import com.vimeo.networking2.enums.StreamType
import java.util.*

data class SubscribeInteraction(

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
    val streamType: StreamType? = null

)
