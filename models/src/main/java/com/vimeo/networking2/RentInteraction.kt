package com.vimeo.networking2

import com.vimeo.networking2.enums.StreamType
import java.util.*

data class RentInteraction(

    /**
     * The currency code for the current user's region.
     */
    val currency: String?,

    /**
     * Formatted price to display to rent an On Demand video.
     */
    val displayPrice: Long?,

    /**
     * Whether the video has DRM.
     */
    val drm: Boolean?,

    /**
     * The time in ISO 8601 format when the rental period for the video expires.
     */
    val expiresTime: Date?,

    /**
     * The URL to rent the On Demand video on Vimeo.
     */
    val link: String,

    /**
     * The numeric value of the price for buying the On Demand video.
     */
    val price: Double?,

    /**
     * The time in ISO 8601 format when the On Demand video was rented.
     */
    val purchaseTime: Date?,

    /**
     * The user's streaming access to this On Demand video:
     */
    val stream: StreamType?,

    /**
     * The product URI to rent the On Demand video.
     */
    val uri: String?

)
