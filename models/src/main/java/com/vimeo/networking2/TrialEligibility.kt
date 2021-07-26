package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal

/**
 * Trial account eligibility.
 *
 * @param eligible Whether or not the user is eligible for a trial period.
 */
@Internal
@JsonClass(generateAdapter = true)
data class TrialEligibility(

    @Internal
    @Json(name = "eligible")
    val eligible: Boolean? = null
)
