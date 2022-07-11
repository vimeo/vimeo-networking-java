package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * LiveEvent embed properties data.
 *
 * @param width The width used to generate the fixed HTML embed code.
 * @param height The height used to generate the fixed HTML embed code.
 * @param sourceUri The source URL used to generate the fixed HTML embed code.
 */
@JsonClass(generateAdapter = true)
data class LiveEventEmbedProperties(

    @Json(name = "width")
    val width: Long? = null,

    @Json(name = "height")
    val height: Long? = null,

    @Json(name = "source_url")
    val sourceUri: String? = null
)
