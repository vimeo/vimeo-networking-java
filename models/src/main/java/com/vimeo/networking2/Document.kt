package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Document data.
 *
 * @param html The partially stripped html for documents like the terms of service.
 */
@JsonClass(generateAdapter = true)
data class Document(

    @Json(name = "html")
    val html: String? = null
)
