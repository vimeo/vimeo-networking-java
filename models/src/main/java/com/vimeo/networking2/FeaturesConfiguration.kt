package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Various feature configuration data.
 *
 * Requires [CapabilitiesType.CAPABILITY_PLATFORM_CONFIGS_OTA_FEATURES].
 */
@JsonClass(generateAdapter = true)
data class FeaturesConfiguration(

    /**
     * Is play tracking enabled?
     */
    @Json(name = "play_tracking")
    val playTracking: Boolean? = null

)
