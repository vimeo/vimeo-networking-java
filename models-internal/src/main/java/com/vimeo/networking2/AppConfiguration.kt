package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal

/**
 * An object returned from the configs endpoint. This is a way for the
 * api to specify configuration for our application.
 */
@JsonClass(generateAdapter = true)
data class AppConfiguration(

    /**
     * API configuration data.
     */
    @Json(name = "api")
    val api: ApiConfiguration? = null,

    /**
     * Facebook API configuration data.
     */
    @Internal
    @Json(name = "facebook")
    val facebook: FacebookConfiguration? = null,

    /**
     * Various feature configuration data.
     */
    @Internal
    @Json(name = "features")
    val features: FeaturesConfiguration? = null,

    /**
     * Various live streaming configuration data.
     */
    @Internal
    @Json(name = "live")
    val live: LiveConfiguration? = null

)
