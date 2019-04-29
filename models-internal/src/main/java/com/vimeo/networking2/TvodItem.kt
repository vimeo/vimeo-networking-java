package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.TvodItemType
import com.vimeo.networking2.enums.asEnum

/**
 * This class represents the model for a Video On Demand (VOD) container.
 */
@JsonClass(generateAdapter = true)
data class TvodItem(

    /**
     * The description of this [TvodItem].
     */
    @Json(name = "description")
    val description: String? = null,

    /**
     * This [TvodItem]'s film, if it is a film.
     */
    @Json(name = "film")
    val film: Video? = null,

    /**
     * The link to the [TvodItem] on Vimeo.
     */
    @Json(name = "link")
    val link: String? = null,

    /**
     * Metadata about [TvodItem].
     */
    @Json(name = "metadata")
    val metadata: Metadata<TvodItemConnections, PurchaseOnDemandInteraction>? = null,

    /**
     * A descriptive title of this [TvodItem].
     */
    @Json(name = "name")
    val name: String? = null,

    /**
     * The active poster for this [TvodItem].
     */
    @Json(name = "pictures")
    val pictures: PictureCollection? = null,

    /**
     * Information on the time the [TvodItem] was published.
     */
    @Json(name = "published")
    val published: Publish? = null,

    /**
     * Whether this [TvodItem] is for a film or a series.
     * @see TvodItem.type
     */
    @Json(name = "type")
    val rawType: String? = null,

    /**
     * The trailer for this [TvodItem].
     */
    @Json(name = "trailer")
    val trailer: Video? = null,

    /**
     * The user who created this [TvodItem].
     */
    @Json(name = "user")
    val user: User? = null

)

/**
 * @see TvodItem.rawType
 * @see TvodItemType
 */
val TvodItem.type: TvodItemType
    get() = rawType.asEnum(TvodItemType.UNKNOWN)
