package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Embed buttons data.
 */
@JsonClass(generateAdapter = true)
data class EmbedButtons(

    /**
     * Whether the Embed button appears in the embeddable player for this video.
     */
    @Json(name = "embed")
    val embed: Boolean? = null,

    /**
     * Whether the Fullscreen button appears in the embeddable player for this video.
     */
    @Json(name = "fullscreen")
    val fullscreen: Boolean? = null,

    /**
     * Whether the HD button appears in the embeddable player for this video.
     */
    @Json(name = "hd")
    val hd: Boolean? = null,

    /**
     * Whether the Like button appears in the embeddable player for this video.
     */
    @Json(name = "like")
    val like: Boolean? = null,

    /**
     * Whether the Scaling button appears in the embeddable player for this video.
     */
    @Json(name = "scaling")
    val scaling: Boolean? = null,

    /**
     * Whether the Share button appears in the embeddable player for this video.
     */
    @Json(name = "share")
    val share: Boolean? = null,

    /**
     * Whether the Watch Later button appears in the embeddable player for this video.
     */
    @Json(name = "watchlater")
    val watchLater: Boolean? = null

)
