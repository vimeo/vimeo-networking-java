package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * User capabilities.
 */
@JsonClass(generateAdapter = true)
class Capabilities {

    /**
     * If user has restricted privacy options enabled.
     */
    @Json(name = "restricted_privacy_options")
    val restrictedPrivacyOptions: Boolean? = null

    /**
     * Whether the user has access to Stock Enterprise.
     */
    @Json(name = "enterprise")
    val enterprise: Boolean? = null
}
