package com.vimeo.networking2.enums

import com.squareup.moshi.Json

/**
 * Embed privacy settings.
 */
enum class EmbedValue {

    /**
     * Only the user can embed their own videos.
     */
    @Json(name = "private")
    PRIVATE,

    /**
     * Anyone can embed the user's videos.
     */
    @Json(name = "public")
    PUBLIC,

    /**
     * Only those on the whitelist can embed the user's videos.
     */
    @Json(name = "whitelist")
    WHITELIST,

    /**
     * Unknown privacy value.
     */
    UNKNOWN
}
