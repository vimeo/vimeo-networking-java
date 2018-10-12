package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Embed data.
 */
@JsonClass(generateAdapter = true)
data class EmbedTitle(

    /**
     * How the embeddable player handles the video title.
     */
    @Json(name = "name")
    val name: String? = null,

    /**
     * How the embeddable player handles the video owner's information.
     */
    @Json(name = "owner")
    val owner: String? = null,

    /**
     * How the embeddable player handles the video owner's portrait.
     */
    @Json(name = "portrait")
    val portrait: String? = null

)
