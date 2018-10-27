package com.vimeo.networking2.enums

import com.squareup.moshi.Json

/**
 * Picture types.
 */
enum class PictureType {

    /**
     * An image that is appropriate for all ages.
     */
    @Json(name = "caution")
    CAUTION,

    /**
     * A custom image for the video.
     */
    @Json(name = "custom")
    CUSTOM,

    /**
     * The default image for the video.
     */
    @Json(name = "default")
    DEFAULT,

    /**
     * Unknown picture type.
     */
    UNKNOWN
}
