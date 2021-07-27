@file:JvmName("BuyInteractionUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal
import com.vimeo.networking2.common.Interaction
import com.vimeo.networking2.enums.DownloadType
import com.vimeo.networking2.enums.StreamAccessType
import com.vimeo.networking2.enums.asEnum
import java.util.Date

/**
 * The buy interaction for a On Demand video.
 *
 * @param currency The currency code for the current user's region.
 * @param displayPrice Formatted price to display to buy an On Demand video.
 * @param download The user's download access to this On Demand video. See [BuyInteraction.downloadType].
 * @param drm Whether the video has DRM.
 * @param link The URL to buy the On Demand video on Vimeo.
 * @param price The numeric value of the price for buying the On Demand video.
 * @param purchaseTime The time in ISO 8601 format when the On Demand video was purchased.
 * @param stream The user's streaming access to this On Demand video. See [BuyInteraction.streamType].
 */
@Internal
@JsonClass(generateAdapter = true)
data class BuyInteraction(

    @Internal
    @Json(name = "currency")
    val currency: String? = null,

    @Internal
    @Json(name = "display_price")
    val displayPrice: String? = null,

    @Internal
    @Json(name = "download")
    val download: String? = null,

    @Internal
    @Json(name = "drm")
    val drm: Boolean? = null,

    @Internal
    @Json(name = "link")
    val link: String? = null,

    @Internal
    @Json(name = "price")
    val price: String? = null,

    @Internal
    @Json(name = "purchase_time")
    val purchaseTime: Date? = null,

    @Internal
    @Json(name = "stream")
    val stream: String? = null,

    @Internal
    @Json(name = "options")
    override val options: List<String>? = null,

    @Internal
    @Json(name = "uri")
    override val uri: String? = null

) : Interaction

/**
 * @see BuyInteraction.download
 * @see DownloadType
 */
val BuyInteraction.downloadType: DownloadType
    get() = download.asEnum(DownloadType.UNKNOWN)

/**
 * @see BuyInteraction.stream
 * @see StreamAccessType
 */
val BuyInteraction.streamType: StreamAccessType
    get() = stream.asEnum(StreamAccessType.UNKNOWN)
