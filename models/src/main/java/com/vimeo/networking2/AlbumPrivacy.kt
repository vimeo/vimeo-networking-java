package com.vimeo.networking2

import com.vimeo.networking2.enums.AlbumPrivacyViewValue

data class AlbumPrivacy(

    /**
     * The privacy-enabled password to see this album. Present only when privacy.view is password.
     */
    val password: String?,

    /**
     * Who can view the album.
     */
    val view: AlbumPrivacyViewValue?
)
