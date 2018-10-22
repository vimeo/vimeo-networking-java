package com.vimeo.networking2.enums

/**
 * Privacy settings for albums.
 */
enum class AlbumPrivacyViewValue {

    /**
     * Anyone can view the album.
     */
    @Json(name = "anybody")
    ANYBODY,

    /**
     * Only owner can see album, can be embedded off-site
     */
    @Json(name = "embed_only")
    EMBED_ONLY,

    /**
     * Only those with the password can view the album.
     */
    @Json(name = "password")
    PASSWORD,

    /**
     * Unknown privacy setting.
     */
    UNKNOWN
}
