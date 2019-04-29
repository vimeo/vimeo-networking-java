@file:JvmName("RentInteractionUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal
import com.vimeo.networking2.enums.StreamAccessType
import com.vimeo.networking2.enums.asEnum
import java.util.*

/**
 * The Rent interaction for an On Demand video.
 */
@Internal
@JsonClass(generateAdapter = true)
data class RentInteraction(

    /**
     * The currency code for the current user's region.
     */
    @Internal
    @Json(name = "currency")
    val currency: String? = null,

    /**
     * Formatted price to display to rent an On Demand video.
     */
    @Internal
    @Json(name = "display_price")
    val displayPrice: Long? = null,

    /**
     * Whether the video has DRM.
     */
    @Internal
    @Json(name = "drm")
    val drm: Boolean? = null,

    /**
     * The time in ISO 8601 format when the rental period for the video expires.
     */
    @Internal
    @Json(name = "expires_time")
    val expirationDate: Date? = null,

    /**
     * The URL to rent the On Demand video on Vimeo.
     */
    @Internal
    @Json(name = "link")
    val link: String? = null,

    /**
     * The numeric value of the price for buying the On Demand video.
     */
    @Internal
    @Json(name = "price")
    val price: Double? = null,

    /**
     * The time in ISO 8601 format when the On Demand video was rented.
     */
    @Internal
    @Json(name = "purchase_time")
    val purchaseTime: Date? = null,

    /**
     * The user's streaming access to this On Demand video.
     * @see RentInteraction.streamAccessType
     */
    @Internal
    @Json(name = "stream")
    val streamAccess: String? = null,

    /**
     * The product URI to rent the On Demand video.
     */
    @Internal
    @Json(name = "uri")
    val uri: String? = null

)

/**
 * @see RentInteraction.streamAccess
 * @see StreamAccessType
 */
val RentInteraction.streamAccessType: StreamAccessType
    get() = streamAccess.asEnum(StreamAccessType.UNKNOWN)
