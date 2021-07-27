package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Video credit data.
 *
 * @param name The name of the person credited.
 * @param role The character that this person portrayed, or the job that this person performed.
 * @param uri The unique identifier to access the credits resource.
 * @param user The Vimeo user associated with this credit.
 * @param video The video associated with this credit.
 */
@JsonClass(generateAdapter = true)
data class Credit(

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "role")
    val role: String? = null,

    @Json(name = "uri")
    val uri: String? = null,

    @Json(name = "user")
    val user: User? = null,

    @Json(name = "video")
    val video: Video? = null

)
