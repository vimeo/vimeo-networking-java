package com.vimeo.networking2
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Live chat configuration data.
 *
 * Requires [CapabilitiesType.CAPABILITY_PLATFORM_CONFIGS_OTA_LIVE].
 */
@JsonClass(generateAdapter = true)
data class LiveChatConfiguration(

    /**
     * The live chat Firebase API key.
     */
    @Json(name = "api_key")
    val apiKey: String? = null,

    /**
     * The live chat Firebase app ID.
     */
    @Json(name = "api_id")
    val appId: String? = null,

    /**
     * The live chat Firebase authentication domain.
     */
    @Json(name = "auth_domain")
    val authDomain: String? = null,

    /**
     * The live chat Firebase database URL.
     */
    @Json(name = "database_url")
    val databaseUrl: String? = null,

    /**
     * The live chat Firebase messaging sender ID.
     */
    @Json(name = "messaging_sender_id")
    val messagingSenderId: String? = null,

    /**
     * The live chat Firebase project ID.
     */
    @Json(name = "project_id")
    val projectId: String? = null,

    /**
     * The live chat Firebase storage bucket.
     */
    @Json(name = "storage_bucket")
    val storageBucket: String? = null

)
