package com.vimeo.networking2.enums

/**
 * Privacy values that can be set for commenting on videos.
 */
enum class CommentPrivacyType(override val value: String?) : StringValue {

    /**
     * Anyone can comment on the user's videos.
     */
    ANYBODY("anybody"),

    /**
     * Only contacts can comment on the user's videos.
     */
    CONTACTS("contacts"),

    /**
     * No one can comment on the user's videos.
     */
    NOBODY("nobody"),

    /**
     * Unknown comment privacy value.
     */
    UNKNOWN(null)
}
