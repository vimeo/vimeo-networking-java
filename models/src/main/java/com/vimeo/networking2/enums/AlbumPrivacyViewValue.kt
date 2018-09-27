package com.vimeo.networking2.enums

enum class AlbumPrivacyViewValue {

    /**
     * Anyone can view the album.
     */
    ANYBODY,

    /**
     * Only owner can see album, can be embedded off-site
     */
    EMBED_ONLY,

    /**
     * Only those with the password can view the album.
     */
    PASSWORD
}
