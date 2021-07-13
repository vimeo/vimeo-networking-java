package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Provides the lists of scopes that are required for third-party connected app features.
 *
 * @param publishToSocial All scopes required for publishing to a specific social media platform.
 * @param simulcast All scopes required for simulcasting to a specific social media platform.
 */
@JsonClass(generateAdapter = true)
data class ConnectedScopes(

    @Json(name = "publish_to_social")
    val publishToSocial: List<String>? = null,

    @Json(name = "simulcast")
    val simulcast: List<String>? = null
)
