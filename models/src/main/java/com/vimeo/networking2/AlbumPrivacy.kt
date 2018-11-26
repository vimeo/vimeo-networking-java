@file:JvmName("AlbumPrivacyUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.AlbumViewPrivacyType
import com.vimeo.networking2.enums.asEnum

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
     * @see AlbumPrivacy.viewPrivacyType
     */
    @Json(name = "view")
    val viewPrivacy: String? = null
)

/**
 * @see AlbumPrivacy.viewPrivacy
 * @see AlbumViewPrivacyType
 */
val AlbumPrivacy.viewPrivacyType: AlbumViewPrivacyType
    get() = viewPrivacy.asEnum(AlbumViewPrivacyType.UNKNOWN)
