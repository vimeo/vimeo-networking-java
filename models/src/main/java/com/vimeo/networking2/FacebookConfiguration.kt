package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Facebook API configuration data.
 *
 * Requires [CapabilitiesType.CAPABILITY_PLATFORM_CONFIGS_OTA_FACEBOOK].
 */
@JsonClass(generateAdapter = true)
data class FacebookConfiguration(

    /**
     * An array of required scopes for connecting users to Facebook.
     */
    @Json(name = "required_scopes")
    val requiredScopes: List<String>? = null

)
