package com.vimeo.networking2.enums

import com.squareup.moshi.Json

/**
 * The type of programmed cinema item.
 */
enum class CinemaType {

    /**
     * Category.
     */
    @Json(name = "category")
    CATEGORY,

    /**
     * Channel.
     */
    @Json(name = "channel")
    CHANNEL,

    /**
     * Unknown cinema item type.
     */
    UNKNOWN
}
