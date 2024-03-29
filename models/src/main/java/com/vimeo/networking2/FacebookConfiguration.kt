package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal

/**
 * Facebook API configuration data.
 *
 * @param requiredScopes An array of required scopes for connecting users to Facebook.
 */
@Internal
@JsonClass(generateAdapter = true)
data class FacebookConfiguration(

    @Internal
    @Json(name = "required_scopes")
    val requiredScopes: List<String>? = null

)
