package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

/**
 * User's email.
 */
@JsonClass(generateAdapter = true)
data class Email(

        @Json(name = "email")
        val email: String? = null

) : Serializable {

    companion object {
        private const val serialVersionUID = -1440735877L
    }
}
