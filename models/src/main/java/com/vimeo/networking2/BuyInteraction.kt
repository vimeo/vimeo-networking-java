package com.vimeo.networking2

import com.vimeo.networking2.common.Interaction
import com.vimeo.networking2.enums.DownloadType
import com.vimeo.networking2.enums.DownloadType.UNKNOWN
import com.vimeo.networking2.enums.StreamType
import java.util.*

/**
 * The buy interaction for a On Demand video.
 */
data class BuyInteraction(

    /**
     * The currency code for the current user's region.
     */
    val currency: String? = null,

    /**
     * Formatted price to display to buy an On Demand video.
     */
    val displayPrice: String? = null,

    /**
     * The user's download access to this On Demand video
     */
    val download: DownloadType = UNKNOWN,

    /**
     * Whether the video has DRM.
     */
    val drm: Boolean? = null,

    /**
     * The URL to buy the On Demand video on Vimeo.
     */
    val link: String? = null,

    /**
     * The numeric value of the price for buying the On Demand video.
     */
    val price: String? = null,

    /**
     * The time in ISO 8601 format when the On Demand video was purchased.
     */
    val purchaseTime: Date? = null,

    /**
     * The user's streaming access to this On Demand video.
     */
    val streamType: StreamType = StreamType.UNKNOWN,

    override val options: List<String>? = null,

    override val uri: String? = null

): Interaction
