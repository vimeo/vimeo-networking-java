package com.vimeo.networking2.enums

import com.squareup.moshi.Json

/**
 * Privacy values that can be set for commenting on videos.
 */
enum class CommentValue {

    /**
     * Anyone can comment on the user's videos.
     */
    @Json(name = "anybody")
    ANYBODY,

    /**
     * Only contacts can comment on the user's videos.
     */
    @Json(name = "contacts")
    CONTACTS,

    /**
     * No one can comment on the user's videos.
     */
    @Json(name = "nobody")
    NOBODY,

    /**
     * Unknown comment privacy value.
     */
    UNKNOWN
}
