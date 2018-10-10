package com.vimeo.networking2

import com.vimeo.networking2.enums.AlbumPrivacyViewValue

/**
 * The privacy set for an album.
 */
data class AlbumPrivacy(

    /**
     * The privacy-enabled password to see this album. Present only when privacy.view is password.
     */
    val password: String? = null,

    /**
     * Who can view the album.
     */
    val view: AlbumPrivacyViewValue? = null
)
