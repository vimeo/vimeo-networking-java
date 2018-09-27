package com.vimeo.networking2.enums

enum class EmbedValue {

    /**
     * Only the user can embed their own videos.
     */
    PRIVATE,

    /**
     * Anyone can embed the user's videos.
     */
    PUBLIC,

    /**
     * Only those on the whitelist can embed the user's videos.
     */
    WHITELIST
}
