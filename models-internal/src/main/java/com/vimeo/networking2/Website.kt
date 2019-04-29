package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * User's website information.
 */
@JsonClass(generateAdapter = true)
data class Website(

    /**
     * The website's description.
     */
    @Json(name = "description")
    val description: String? = null,

    /**
     * The URL of the website.
     */
    @Json(name = "link")
    val link: String? = null,

    /**
     * The name of the website.
     */
    @Json(name = "users_with_access")
    val name: String? = null
)
