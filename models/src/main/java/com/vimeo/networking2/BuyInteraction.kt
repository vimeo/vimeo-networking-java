package com.vimeo.networking2

import com.vimeo.networking2.enums.DownloadType
import com.vimeo.networking2.enums.StreamType
import java.util.*

data class BuyInteraction(

    /**
     * The currency code for the current user's region.
     */
    val currency: String?,

    /**
     * Formatted price to display to buy an On Demand video.
     */
    val displayPrice: String?,

    /**
     * The user's download access to this On Demand video
     */
    val download: DownloadType?,

    /**
     * Whether the video has DRM.
     */
    val drm: Boolean?,

    /**
     * The URL to buy the On Demand video on Vimeo.
     */
    val link: String?,

    /**
     * The numeric value of the price for buying the On Demand video.
     */
    val price: String?,

    /**
     * The time in ISO 8601 format when the On Demand video was purchased.
     */
    val purchaseTime: Date?,

    /**
     * The user's streaming access to this On Demand video.
     */
    val streamType: StreamType?,

    /**
     * The product URI to purchase the On Demand video.
     */
    val uri: String?

)
