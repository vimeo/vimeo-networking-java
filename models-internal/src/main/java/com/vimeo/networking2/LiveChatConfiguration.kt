package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal

/**
 * Live chat configuration data.
 */
@Internal
@JsonClass(generateAdapter = true)
data class LiveChatConfiguration(

    /**
     * The live chat Firebase API key.
     */
    @Internal
    @Json(name = "api_key")
    val apiKey: String? = null,

    /**
     * The live chat Firebase app ID.
     */
    @Internal
    @Json(name = "app_id")
    val appId: String? = null,

    /**
     * The live chat Firebase authentication domain.
     */
    @Internal
    @Json(name = "auth_domain")
    val authDomain: String? = null,

    /**
     * The live chat Firebase database URL.
     */
    @Internal
    @Json(name = "database_url")
    val databaseUrl: String? = null,

    /**
     * The live chat Firebase messaging sender ID.
     */
    @Internal
    @Json(name = "messaging_sender_id")
    val messagingSenderId: String? = null,

    /**
     * The live chat Firebase project ID.
     */
    @Internal
    @Json(name = "project_id")
    val projectId: String? = null,

    /**
     * The live chat Firebase storage bucket.
     */
    @Internal
    @Json(name = "storage_bucket")
    val storageBucket: String? = null

)
