package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.TrialType
import com.vimeo.networking2.enums.asEnum

/**
 * Information about the user's trial period.
 */
@JsonClass(generateAdapter = true)
data class SubscriptionTrial(

    /**
     * The status of the user's trial.
     * If the value is "free_trial" the user is currently in a free trial.
     */
    @Json(name = "status")
    var status: String

)

val SubscriptionTrial.trialStatus: TrialType
    get() = status.asEnum(TrialType.UNKNOWN)
