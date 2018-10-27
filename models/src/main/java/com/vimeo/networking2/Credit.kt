package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Video credit data.
 */
@JsonClass(generateAdapter = true)
data class Credit(

    /**
     * The name of the person credited.
     */
    @Json(name = "name")
    val name: String? = null,

    /**
     * The character that this person portrayed, or the job that this person performed.
     */
    @Json(name = "role")
    val role: String? = null,

    /**
     * The unique identifier to access the credits resource.
     */
    @Json(name = "uri")
    val uri: String? = null,

    /**
     * The Vimeo user associated with this credit.
     */
    @Json(name = "user")
    val user: User? = null,

    /**
     * The video associated with this credit.
     */
    @Json(name = "video")
    val video: Video? = null

)
