package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.AlbumPrivacyViewValue

/**
 * The privacy set for an album.
 */
@JsonClass(generateAdapter = true)
data class AlbumPrivacy(

    /**
     * The privacy-enabled password to see this album. Present only when privacy.view is password.
     */
    @Json(name = "password")
    val password: String? = null,

    /**
     * Who can view the album.
     */
    @Json(name = "view")
    val viewingPermissions: AlbumPrivacyViewValue? = null
)
