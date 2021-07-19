@file:JvmName("PictureCollectionUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.Entity
import com.vimeo.networking2.enums.PictureType
import com.vimeo.networking2.enums.asEnum

/**
 * Collection of pictures.
 *
 * @param active Whether this picture is the active picture for its parent resource.
 * @param link The upload URL for the picture. This field appears when you create the picture resource for the first
 * time.
 * @param rawType The type of the picture. See [PictureCollection.type].
 * @param resourceKey The picture's resource key string.
 * @param sizes An array containing reference information about all available image files.
 * @param uri The picture's URI.
 */
@JsonClass(generateAdapter = true)
data class PictureCollection(

    @Json(name = "active")
    val active: Boolean? = null,

    @Json(name = "link")
    val link: String? = null,

    @Json(name = "type")
    val rawType: String? = null,

    @Json(name = "resource_key")
    val resourceKey: String? = null,

    @Json(name = "sizes")
    val sizes: List<Picture>? = null,

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
