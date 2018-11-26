package com.vimeo.networking2.enums

/**
 * Embed privacy settings.
 */
enum class EmbedPrivacyType(override val value: String?) : StringValue {

    /**
     * Only the user can embed their own videos.
     */
    PRIVATE("private"),

    /**
     * Anyone can embed the user's videos.
     */
    PUBLIC("public"),

    /**
     * Only those on the whitelist can embed the user's videos.
     */
    WHITELIST("whitelist"),

    /**
     * Unknown privacy value.
     */
    UNKNOWN(null)
}
