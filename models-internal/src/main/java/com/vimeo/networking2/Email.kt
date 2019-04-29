package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * User's email.
 */
@JsonClass(generateAdapter = true)
data class Email(

    @Json(name = "email")
    val email: String? = null

)
