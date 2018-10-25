package com.vimeo.networking2.enums

import com.squareup.moshi.Json

/**
 * Privacy values that can be set for viewing a video.
 */
enum class ViewValue {

    /**
     * Anyone can view the user's videos.
     */
    @Json(name = "anybody")
    ANYBODY,

    /**
     * Only contacts can view the user's videos.
     */
    @Json(name = "contacts")
    CONTACTS,

    /**
     * Views are disabled for the user's videos.
     */
    @Json(name = "disable")
    DISABLE,

    /**
     * No one except the user can view the user's videos.
     */
    @Json(name = "nobody")
    NOBODY,

    /**
     * Only those with the password can view the user's videos.
     */
    @Json(name = "password")
    PASSWORD,

    /**
     * Anybody can view the user's videos if they have a link.
     */
    @Json(name = "unlisted")
    UNLISTED,

    /**
     * Only other Vimeo members can view the user's videos.
     */
    @Json(name = "users")
    USERS,

    /**
     * Unknown privacy value.
     */
    UNKNOWN
}
