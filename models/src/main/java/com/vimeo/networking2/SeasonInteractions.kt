package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal

/**
 * All actions that can be taken on a [Season].
 */
@JsonClass(generateAdapter = true)
data class SeasonInteractions(

    /**
     * The interactions for an On Demand video.
     */
    @Internal
    @Json(name = "purchase")
    val purchase: PurchaseOnDemandInteraction? = null

)
