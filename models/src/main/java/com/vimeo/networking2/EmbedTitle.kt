package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Embed data.
 *
 * @param name How the embeddable player handles the video title.
 * @param owner How the embeddable player handles the video owner's information.
 * @param portrait How the embeddable player handles the video owner's portrait.
 */
@JsonClass(generateAdapter = true)
data class EmbedTitle(

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "owner")
    val owner: String? = null,

    @Json(name = "portrait")
    val portrait: String? = null

)
