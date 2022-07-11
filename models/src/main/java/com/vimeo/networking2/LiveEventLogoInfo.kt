package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Live Event Logos data.
 *
 * @param customLogo A collection of information relating to custom logos in the embeddable player.
 * @param shouldShowVimeoLogo Whether the Vimeo logo appears in the embeddable player for the video.
 */
@JsonClass(generateAdapter = true)
data class LiveEventLogoInfo(

    @Json(name = "custom")
    val customLogo: LiveEventCustomLogo? = null,

    @Json(name = "vimeo")
    val shouldShowVimeoLogo: Boolean? = null
)
