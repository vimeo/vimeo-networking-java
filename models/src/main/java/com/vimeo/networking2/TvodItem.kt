@file:JvmName("TvodItemUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.TvodItemType
import com.vimeo.networking2.enums.asEnum

/**
 * This class represents the model for a Video On Demand (VOD) container.
 *
 * @param description The description of this [TvodItem].
 * @param film This [TvodItem's][TvodItem] film, if it is a film.
 * @param link The link to the [TvodItem] on Vimeo.
 * @param metadata Metadata about [TvodItem].
 * @param name A descriptive title of this [TvodItem].
 * @param pictures The active poster for this [TvodItem].
 * @param published Information on the time the [TvodItem] was published.
 * @param rawType Whether this [TvodItem] is for a film or a series. See [TvodItem.type].
 * @param trailer The trailer for this [TvodItem].
 * @param user The user who created this [TvodItem].
 */
@JsonClass(generateAdapter = true)
data class TvodItem(

    @Json(name = "description")
    val description: String? = null,

    @Json(name = "film")
    val film: Video? = null,

    @Json(name = "link")
    val link: String? = null,

    @Json(name = "metadata")
    val metadata: Metadata<TvodItemConnections, PurchaseOnDemandInteraction>? = null,

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "pictures")
    val pictures: PictureCollection? = null,

    @Json(name = "published")
    val published: Publish? = null,

    @Json(name = "type")
    val rawType: String? = null,

    @Json(name = "trailer")
    val trailer: Video? = null,

    @Json(name = "user")
    val user: User? = null

)

/**
 * @see TvodItem.rawType
 * @see TvodItemType
 */
val TvodItem.type: TvodItemType
    get() = rawType.asEnum(TvodItemType.UNKNOWN)
