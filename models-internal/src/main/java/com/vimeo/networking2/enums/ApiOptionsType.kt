package com.vimeo.networking2.enums

/**
 * Type of request that can be made for a URI.
 */
enum class ApiOptionsType(override val value: String?) : StringValue {
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE"),
    UNKNOWN(null)
}
