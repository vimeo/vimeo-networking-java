package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal

/**
 * Live Stream Quota DTO.
 *
 * @param maximum The maximum amount of streams that the user can create.
 * @param remaining The amount of remaining live streams that the user can create this month.
 */
@Internal
@JsonClass(generateAdapter = true)
data class LiveStreamsQuota(

    @Internal
    @Json(name = "maximum")
    val maximum: Int? = null,

    @Internal
    @Json(name = "remaining")
    val remaining: Int? = null

)
