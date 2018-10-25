package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Requires [CapabilitiesType.CAPABILITY_PLAY_PROGRESS].
 */
@JsonClass(generateAdapter = true)
data class PlayProgress(

    /**
     * The play progress in seconds.
     */
    @Json(name = "seconds")
    val seconds: Int? = null

)
