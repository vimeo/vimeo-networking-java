@file:JvmName("PictureCollectionUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.Entity
import com.vimeo.networking2.enums.PictureType
import com.vimeo.networking2.enums.asEnum

/**
 * Collection of pictures.
 */
@JsonClass(generateAdapter = true)
data class PictureCollection(

    /**
     * Whether this picture is the active picture for its parent resource.
     */
    @Json(name = "active")
    val active: Boolean? = null,

    /**
     * The upload URL for the picture. This field appears when you create the
     * picture resource for the first time.
     */
    @Json(name = "link")
    val link: String? = null,

    /**
     * The picture's resource key string.
     */
    @Json(name = "resource_key")
    val resourceKey: String? = null,

    /**
     * An array containing reference information about all available image files
     */
    @Json(name = "sizes")
    val sizes: List<Picture>? = null,

    /**
     * The type of the picture.
     * @see PictureCollection.type
     */
    @Json(name = "type")
    val rawType: String? = null,

    /**
     * The picture's URI.
     */
    @Json(name = "uri")
    val uri: String? = null

) : Entity {

    override val identifier: String? = resourceKey

}

/**
 * @see PictureCollection.rawType
 * @see PictureType
 */
val PictureCollection.type: PictureType
    get() = rawType.asEnum(PictureType.UNKNOWN)
