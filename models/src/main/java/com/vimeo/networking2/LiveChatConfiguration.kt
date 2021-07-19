package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal

/**
 * Live chat configuration data.
 *
 * @param apiKey The live chat Firebase API key.
 * @param appId The live chat Firebase app ID.
 * @param authDomain The live chat Firebase authentication domain.
 * @param databaseUrl The live chat Firebase database URL.
 * @param messagingSenderId The live chat Firebase messaging sender ID.
 * @param projectId The live chat Firebase project ID.
 * @param storageBucket The live chat Firebase storage bucket.
 */
@Internal
@JsonClass(generateAdapter = true)
data class LiveChatConfiguration(

    @Internal
    @Json(name = "api_key")
    val apiKey: String? = null,

    @Internal
    @Json(name = "app_id")
    val appId: String? = null,

    @Internal
    @Json(name = "auth_domain")
    val authDomain: String? = null,

    @Internal
    @Json(name = "database_url")
    val databaseUrl: String? = null,

    @Internal
    @Json(name = "messaging_sender_id")
    val messagingSenderId: String? = null,

    @Internal
    @Json(name = "project_id")
    val projectId: String? = null,

    @Internal
    @Json(name = "storage_bucket")
    val storageBucket: String? = null

)
