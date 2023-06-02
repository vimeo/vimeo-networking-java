package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * The app information.
 *
 * @param uri an app uri.
 * @param name an app name.
 */
@JsonClass(generateAdapter = true)
data class App(
    @Json(name = "uri")
    val uri: String? = null,

    @Json(name = "name")
    val name: String? = null
)
