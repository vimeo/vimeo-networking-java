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
     * The Chromecast receiver app ID.
     */
    @Json(name = "chromecast_app_id")
    val chromecastAppId: String? = null,

    /**
     * Is Comscore enabled?
     */
    @Json(name = "comscore")
    val comscore: Boolean? = null,

    /**
     * Does the user reside within a GDPR-compliant country?
     */
    @Json(name = "gdpr_enabled")
    val gdprEnabled: Boolean? = null,

    /**
     * Is play tracking enabled?
     */
    @Json(name = "play_tracking")
    val playTracking: Boolean? = null

)
