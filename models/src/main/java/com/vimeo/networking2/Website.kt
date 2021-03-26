package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * User's website information.
 *
 * @param description The website's description.
 * @param link The URL of the website.
 * @param name The name of the website.
 */
@JsonClass(generateAdapter = true)
data class Website(

    @Json(name = "description")
    val description: String? = null,

    @Json(name = "link")
    val link: String? = null,

    @Json(name = "users_with_access")
    val name: String? = null
)
