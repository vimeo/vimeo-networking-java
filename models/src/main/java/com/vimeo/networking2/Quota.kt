package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Quota data.
 *
 * @param hd Whether you can upload HD videos.
 * @param sd Whether you can upload SD videos.
 */
@JsonClass(generateAdapter = true)
data class Quota(

    @Json(name = "hd")
    val hd: Boolean? = null,

    @Json(name = "sd")
    val sd: Boolean? = null

)
