package com.vimeo.networking2

/**
 * Pictures information such as dimensions, link to image, etc...
 */
data class Picture(

    /**
     * The height of the image.
     */
    val height: Int? = null,

    /**
     * The direct link to the image.
     */
    val link: String? = null,

    /**
     * The direct link to the image with a play button overlay.
     */
    val linkWithPlayButton: String? = null,

    /**
     * The picture resource key.
     */
    val resourceKey: String? = null,

    /**
     * The width of the image.
     */
    val width: Int? = null

): Entity {

    override val identifier: String? = resourceKey

}
