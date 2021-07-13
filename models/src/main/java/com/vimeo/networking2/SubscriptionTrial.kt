@file:JvmName("SubscriptionTrialUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.TrialStatusType
import com.vimeo.networking2.enums.asEnum

/**
 * Information about the user's trial period.
 *
 * @param rawStatus The status of the user's trial. If the value is "free_trial" the user is currently in a free trial.
 * @param hasBeenInFreeTrial Has the user been in (or is currently in) a free trial.
 */
@JsonClass(generateAdapter = true)
data class SubscriptionTrial(

    @Json(name = "status")
    val rawStatus: String? = null,

    @Json(name = "has_been_in_free_trial")
    val hasBeenInFreeTrial: Boolean? = null
)

/**
 * @see [SubscriptionTrial.rawStatus]
 * @see TrialStatusType
 */
val SubscriptionTrial.status: TrialStatusType
    get() = rawStatus.asEnum(TrialStatusType.UNKNOWN)
