package com.vimeo.networking2.enums

/**
 * Video settings privacy view values.
 */
enum class ViewValue {

    /**
     * Anyone can view the user's videos.
     */
    ANYBODY,

    /**
     * Only contacts can view the user's videos.
     */
    CONTACTS,

    /**
     * Views are disabled for the user's videos.
     */
    DISABLE,

    /**
     * No one except the user can view the user's videos.
     */
    NOBODY,

    /**
     * Only those with the password can view the user's videos.
     */
    PASSWORD,

    /**
     * Anybody can view the user's videos if they have a link.
     */
    UNLISTED,

    /**
     * Only other Vimeo members can view the user's videos.
     */
    USERS,

    /**
     * Unknown view privacy value.
     */
    UNKNOWN
}
