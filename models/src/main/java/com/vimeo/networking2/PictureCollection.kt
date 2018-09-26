package com.vimeo.networking2

import com.vimeo.networking2.enums.PictureType

data class PictureCollection(

    /**
     * Whether this picture is the active picture for its parent resource.
     */
    val active: Boolean?,

    /**
     * The upload URL for the picture. This field appears when you create the
     * picture resource for the first time.
     */
    val link: String?,

    /**
     * The picture's resource key string.
     */
    val resourceKey: String?,

    /**
     * An array containing reference information about all available image files
     */
    val picture: List<Picture>?,

    /**
     * The type of the picture.
     */
    val type: PictureType?,

    /**
     * The picture's URI.
     */
    val uri: String?

)
