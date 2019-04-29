package com.vimeo.networking2.enums

/**
 * Privacy settings for albums.
 */
enum class AlbumViewPrivacyType(override val value: String?): StringValue {

    /**
     * Anyone can view the album.
     */
    ANYBODY("anybody"),

    /**
     * Only owner can see album, can be embedded off-site.
     */
    EMBED_ONLY("embed_only"),

    /**
     * Only those with the password can view the album.
     */
    PASSWORD("password"),

    /**
     * Unknown privacy setting.
     */
    UNKNOWN(null)
}
