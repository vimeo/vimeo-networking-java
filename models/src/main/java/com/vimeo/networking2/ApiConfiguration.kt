package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * API Configuration data.
 */
@JsonClass(generateAdapter = true)
data class ApiConfiguration(

    /**
     * URL to access the API.
     */
    @Json(name = "host")
    val host: String? = null

)
