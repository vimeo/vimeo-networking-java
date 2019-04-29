package com.vimeo.networking2.enums

/**
 * Picture types.
 */
enum class PictureType(override val value: String?) : StringValue {

    /**
     * An image that is appropriate for all ages.
     */
    CAUTION("caution"),

    /**
     * A custom image for the video.
     */
    CUSTOM("custom"),

    /**
     * The default image for the video.
     */
    DEFAULT("default"),

    /**
     * Unknown picture type.
     */
    UNKNOWN(null)
}
