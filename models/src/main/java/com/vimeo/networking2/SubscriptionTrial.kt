package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.TrialStatusType
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
    var rawStatus: String? = null,

    /**
     * Has the user been in (or is currently in) a free trial.
     */
    @Json(name = "has_been_in_free_trial")
    var hasBeenInFreeTrial: Boolean? = null
)

/**
 * @see [SubscriptionTrial.rawStatus]
 * @see TrialStatusType
 */
val SubscriptionTrial.status: TrialStatusType
    get() = rawStatus.asEnum(TrialStatusType.UNKNOWN)
