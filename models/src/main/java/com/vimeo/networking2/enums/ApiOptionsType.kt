package com.vimeo.networking2.enums

import com.squareup.moshi.Json

/**
 * Type of request that can be made for a URI.
 */
enum class ApiOptionsType {
    @Json(name = "GET")
    GET,
    @Json(name = "POST")
    POST,
    @Json(name = "PUT")
    PUT,
    @Json(name = "DELETE")
    DELETE,
    UNKNOWN
}
