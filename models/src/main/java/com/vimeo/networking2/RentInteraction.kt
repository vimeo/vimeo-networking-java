package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.StreamType
import java.util.*

/**
 * The Rent interaction for an On Demand video.
 */
@JsonClass(generateAdapter = true)
data class RentInteraction(

    /**
     * The currency code for the current user's region.
     */
    @Json(name = "currency")
    val currency: String? = null,

    /**
     * Formatted price to display to rent an On Demand video.
     */
    @Json(name = "display_price")
    val displayPrice: Long? = null,

    /**
     * Whether the video has DRM.
     */
    @Json(name = "drm")
    val drm: Boolean? = null,

    /**
     * The time in ISO 8601 format when the rental period for the video expires.
     */
    @Json(name = "expires_time")
    val expirationDate: Date? = null,

    /**
     * The URL to rent the On Demand video on Vimeo.
     */
    @Json(name = "link")
    val link: String,

    /**
     * The numeric value of the price for buying the On Demand video.
     */
    @Json(name = "price")
    val price: Double? = null,

    /**
     * The time in ISO 8601 format when the On Demand video was rented.
     */
    @Json(name = "purchase_time")
    val purchaseTime: Date? = null,

    /**
     * The user's streaming access to this On Demand video:
     */
    @Json(name = "stream")
    val stream: StreamType? = null,

    /**
     * The product URI to rent the On Demand video.
     */
    @Json(name = "uri")
    val uri: String? = null

)
