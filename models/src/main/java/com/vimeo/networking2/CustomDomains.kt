package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Custom domains data.
 *
 * @param authDomain auth domain url.
 * @param apiDomain api domain url.
 * @param magistoApiDomain Magisto api domain url.
 */
@JsonClass(generateAdapter = true)
data class CustomDomains(

    @Json(name = "auth_domain")
    val authDomain: String? = null,

    @Json(name = "api_domain")
    val apiDomain: String? = null,

    @Json(name = "magisto_api_domain")
    val magistoApiDomain: String? = null
)
