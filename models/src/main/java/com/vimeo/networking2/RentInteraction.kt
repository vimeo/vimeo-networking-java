package com.vimeo.networking2

import com.vimeo.networking2.enums.StreamType
import com.vimeo.networking2.enums.StreamType.UNKNOWN
import java.util.*

/**
 * The Rent interaction for an On Demand video.
 */
data class RentInteraction(

    /**
     * The currency code for the current user's region.
     */
    val currency: String? = null,

    /**
     * Formatted price to display to rent an On Demand video.
     */
    val displayPrice: Long? = null,

    /**
     * Whether the video has DRM.
     */
    val drm: Boolean? = null,

    /**
     * The time in ISO 8601 format when the rental period for the video expires.
     */
    val expirationDate: Date? = null,

    /**
     * The URL to rent the On Demand video on Vimeo.
     */
    val link: String,

    /**
     * The numeric value of the price for buying the On Demand video.
     */
    val price: Double? = null,

    /**
     * The time in ISO 8601 format when the On Demand video was rented.
     */
    val purchaseTime: Date? = null,

    /**
     * The user's streaming access to this On Demand video:
     */
    val stream: StreamType = UNKNOWN,

    /**
     * The product URI to rent the On Demand video.
     */
    val uri: String? = null

)
