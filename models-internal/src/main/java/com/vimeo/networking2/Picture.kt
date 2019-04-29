package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.Entity

/**
 * Pictures information such as dimensions, link to image, etc...
 */
@JsonClass(generateAdapter = true)
data class Picture(

    /**
     * The height of the image.
     */
    @Json(name = "height")
    val height: Int? = null,

    /**
     * The direct link to the image.
     */
    @Json(name = "link")
    val link: String? = null,

    /**
     * The direct link to the image with a play button overlay.
     */
    @Json(name = "link_with_play_button")
    val linkWithPlayButton: String? = null,

    /**
     * The picture resource key.
     */
    val resourceKey: String? = null,

    /**
     * The width of the image.
     */
    @Json(name = "width")
    val width: Int? = null

) : Entity {

    override val identifier: String? = resourceKey

}
