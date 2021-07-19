@file:JvmName("AlbumPrivacyUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.AlbumViewPrivacyType
import com.vimeo.networking2.enums.asEnum

/**
 * The privacy set for an album.
 *
 * @param password The privacy-enabled password to see this album. Present only when privacy.view is password.
 * @param viewPrivacy Who can view the album. See [AlbumPrivacy.viewPrivacyType].
 */
@JsonClass(generateAdapter = true)
data class AlbumPrivacy(

    @Json(name = "password")
    val password: String? = null,

    @Json(name = "view")
    val viewPrivacy: String? = null
)

/**
 * @see AlbumPrivacy.viewPrivacy
 * @see AlbumViewPrivacyType
 */
val AlbumPrivacy.viewPrivacyType: AlbumViewPrivacyType
    get() = viewPrivacy.asEnum(AlbumViewPrivacyType.UNKNOWN)
