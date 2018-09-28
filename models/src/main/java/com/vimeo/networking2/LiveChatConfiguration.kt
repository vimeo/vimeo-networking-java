package com.vimeo.networking2

/**
 * Based on CAPABILITY_PLATFORM_CONFIGS_OTA_LIVE.
 */
data class LiveChatConfiguration(

    /**
     * The live chat Firebase API key.
     */
    val apiKey: String?,

    /**
     * The live chat Firebase app ID.
     */
    val appId: String?,

    /**
     * The live chat Firebase authentication domain.
     */
    val authDomain: String?,

    /**
     * The live chat Firebase database URL.
     */
    val databaseUrl: String?,

    /**
     * The live chat Firebase messaging sender ID.
     */
    val messagingSenderId: String?,

    /**
     * The live chat Firebase project ID.
     */
    val projectId: String?,

    /**
     * The live chat Firebase storage bucket.
     */
    val storageBucket: String?

)
