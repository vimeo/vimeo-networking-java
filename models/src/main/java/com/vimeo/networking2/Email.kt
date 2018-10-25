package com.vimeo.networking2

import com.squareup.moshi.Json

/**
 * User's email.
 */
data class Email(

    @Json(name = "email")
    val email: String? = null

)
