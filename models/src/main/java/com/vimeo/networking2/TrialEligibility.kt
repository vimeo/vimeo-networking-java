package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Trial account eligibility.
 */
@JsonClass(generateAdapter = true)
data class TrialEligibility(

    /**
     * `true` or `false` depending on if the user is eligible for a trial period.
     */
    @Json(name = "eligible")
    val eligible: Boolean? = null
)
