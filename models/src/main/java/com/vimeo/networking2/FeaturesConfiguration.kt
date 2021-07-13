package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal

/**
 * Various feature configuration data.
 *
 * @param playTracking Is play tracking enabled?
 */
@Internal
@JsonClass(generateAdapter = true)
data class FeaturesConfiguration(

    @Internal
    @Json(name = "play_tracking")
    val playTracking: Boolean? = null

)
