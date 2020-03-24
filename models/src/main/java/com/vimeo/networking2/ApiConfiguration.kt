package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

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

) : Serializable {
    companion object {
        private const val serialVersionUID = -8L
    }
}
