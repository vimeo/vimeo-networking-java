package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.Entity

/**
 * Information about a picture (dimensions, link to image, etc.).
 *
 * @param height The height of the image.
 * @param link The direct link to the image.
 * @param linkWithPlayButton The direct link to the image with a play button overlay.
 * @param resourceKey The picture resource key.
 * @param width The width of the image.
 */
@JsonClass(generateAdapter = true)
data class Picture(

    @Json(name = "height")
    val height: Int? = null,

    @Json(name = "link")
    val link: String? = null,

    @Json(name = "link_with_play_button")
    val linkWithPlayButton: String? = null,

    val resourceKey: String? = null,

    @Json(name = "width")
    val width: Int? = null

) : Entity {
    override val identifier: String? = resourceKey
}
