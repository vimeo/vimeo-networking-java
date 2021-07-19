package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal

/**
 * An object returned from the configs endpoint. This is a way for the API to specify configuration for our application.
 *
 * @param api API configuration data.
 * @param facebook Facebook API configuration data.
 * @param features Various feature configuration data.
 * @param live Various live streaming configuration data.
 */
@JsonClass(generateAdapter = true)
data class AppConfiguration(

    @Json(name = "api")
    val api: ApiConfiguration? = null,

    @Internal
    @Json(name = "facebook")
    val facebook: FacebookConfiguration? = null,

    @Internal
    @Json(name = "features")
    val features: FeaturesConfiguration? = null,

    @Internal
    @Json(name = "live")
    val live: LiveConfiguration? = null
)
