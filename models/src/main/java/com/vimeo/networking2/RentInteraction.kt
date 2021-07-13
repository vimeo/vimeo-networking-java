@file:JvmName("RentInteractionUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal
import com.vimeo.networking2.enums.StreamAccessType
import com.vimeo.networking2.enums.asEnum
import java.util.Date

/**
 * The Rent interaction for an On Demand video.
 *
 * @param currency The currency code for the current user's region.
 * @param displayPrice Formatted price to display to rent an On Demand video.
 * @param drm Whether the video has DRM.
 * @param expirationDate The time in ISO 8601 format when the rental period for the video expires.
 * @param link The URL to rent the On Demand video on Vimeo.
 * @param price The numeric value of the price for buying the On Demand video.
 * @param purchaseTime The time in ISO 8601 format when the On Demand video was rented.
 * @param streamAccess The user's streaming access to this On Demand video. See [RentInteraction.streamAccessType].
 * @param uri The product URI to rent the On Demand video.
 */
@Internal
@JsonClass(generateAdapter = true)
data class RentInteraction(

    @Internal
    @Json(name = "currency")
    val currency: String? = null,

    @Internal
    @Json(name = "display_price")
    val displayPrice: Long? = null,

    @Internal
    @Json(name = "drm")
    val drm: Boolean? = null,

    @Internal
    @Json(name = "expires_time")
    val expirationDate: Date? = null,

    @Internal
    @Json(name = "link")
    val link: String? = null,

    @Internal
    @Json(name = "price")
    val price: Double? = null,

    @Internal
    @Json(name = "purchase_time")
    val purchaseTime: Date? = null,

    @Internal
    @Json(name = "stream")
    val streamAccess: String? = null,

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
