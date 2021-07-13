package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Embed buttons data.
 *
 * @param embed Whether the Embed button appears in the embeddable player for this video.
 * @param fullscreen Whether the Fullscreen button appears in the embeddable player for this video.
 * @param hd Whether the HD button appears in the embeddable player for this video.
 * @param like Whether the Like button appears in the embeddable player for this video.
 * @param scaling Whether the Scaling button appears in the embeddable player for this video.
 * @param share Whether the Share button appears in the embeddable player for this video.
 * @param watchLater Whether the Watch Later button appears in the embeddable player for this video.
 */
@JsonClass(generateAdapter = true)
data class EmbedButtons(

    @Json(name = "embed")
    val embed: Boolean? = null,

    @Json(name = "fullscreen")
    val fullscreen: Boolean? = null,

    @Json(name = "hd")
    val hd: Boolean? = null,

    @Json(name = "like")
    val like: Boolean? = null,

    @Json(name = "scaling")
    val scaling: Boolean? = null,

    @Json(name = "share")
    val share: Boolean? = null,

    @Json(name = "watchlater")
    val watchLater: Boolean? = null

)
