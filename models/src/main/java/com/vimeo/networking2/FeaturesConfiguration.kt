package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal

/**
 * Various feature configuration data.
 */
@Internal
@JsonClass(generateAdapter = true)
data class FeaturesConfiguration(

    /**
     * Is play tracking enabled?
     */
    @Internal
    @Json(name = "play_tracking")
    val playTracking: Boolean? = null

)
