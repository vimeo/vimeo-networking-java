package com.vimeo.networking2.enums

/**
 * Privacy values that can be set for viewing a video.
 */
enum class ViewPrivacyType(override val value: String?) : StringValue {

    /**
     * Anyone can view the user's videos.
     */
    ANYBODY("anybody"),

    /**
     * This is a stock video and may have limited access restrictions.
     */
    STOCK("stock"),

    /**
     * Only contacts can view the user's videos.
     */
    CONTACTS("contacts"),

    /**
     * Views are disabled for the user's videos.
     */
    DISABLE("disable"),

    /**
     * No one except the user can view the user's videos.
     */
    NOBODY("nobody"),

    /**
     * Only those with the password can view the user's videos.
     */
    PASSWORD("password"),

    /**
     * Anybody can view the user's videos if they have a link.
     */
    UNLISTED("unlisted"),

    /**
     * Only other Vimeo members can view the user's videos.
     */
    USERS("users"),

    /**
     * Unknown privacy value.
     */
    UNKNOWN(null)
}
