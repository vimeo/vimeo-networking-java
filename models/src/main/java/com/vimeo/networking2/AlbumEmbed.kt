package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Embed data for a album.
 */
@JsonClass(generateAdapter = true)
data class AlbumEmbed(

    /**
     * The responsive HTML code to embed the playlist on a website. This is present only
     * when privacy.view is not password and when the album has embeddable clips.
     */
    @Json(name = "html")
    val html: String? = null
)
