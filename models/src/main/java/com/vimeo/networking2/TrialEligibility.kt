package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal

/**
 * Trial account eligibility.
 */
@Internal
@JsonClass(generateAdapter = true)
data class TrialEligibility(

    /**
     * `true` or `false` depending on if the user is eligible for a trial period.
     */
    @Internal
    @Json(name = "eligible")
    val eligible: Boolean? = null
)
