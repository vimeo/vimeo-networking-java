package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Live Event custom logo data.
 *
 * @param isActive Whether the custom logo appears in the embeddable player.
 * @param isSticky Whether the custom logo appears even when the player interface is hidden.
 * @param link The URL that loads upon clicking the custom logo.
 * @param shouldUseLink Whether the custom logo should use the URL link.
 * @param url The URL source of the custom player logo.
 */
@JsonClass(generateAdapter = true)
data class LiveEventCustomLogo(

    @Json(name = "active")
    val isActive: Boolean? = null,

    @Json(name = "sticky")
    val isSticky: Boolean? = null,

    @Json(name = "link")
    val link: String? = null,

    @Json(name = "use_link")
    val shouldUseLink: Boolean? = null,

    @Json(name = "url")
    val url: String? = null
)
