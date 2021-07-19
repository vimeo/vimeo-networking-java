package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.AlbumViewPrivacyType

/**
 * Embed data for a album.
 *
 * @param html The responsive HTML code to embed the playlist on a website. This is present only when
 * [AlbumPrivacy.viewPrivacyType] is not [AlbumViewPrivacyType.PASSWORD] and when the album has embeddable clips.
 */
@JsonClass(generateAdapter = true)
data class AlbumEmbed(

    @Json(name = "html")
    val html: String? = null
)
