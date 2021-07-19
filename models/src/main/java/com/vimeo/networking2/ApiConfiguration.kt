package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * API Configuration data.
 *
 * @param host URL to access the API.
 */
@JsonClass(generateAdapter = true)
data class ApiConfiguration(

    @Json(name = "host")
    val host: String? = null
)
