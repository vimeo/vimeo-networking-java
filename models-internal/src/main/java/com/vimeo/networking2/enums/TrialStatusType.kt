package com.vimeo.networking2.enums

/**
 * Status types for a user's trial.
 */
enum class TrialStatusType(override val value: String?) : StringValue {

    /**
     * The user is currently in a free trial.
     */
    FREE_TRIAL("free_trial"),

    /**
     * Unknown trial type.
     */
    UNKNOWN(null)
}
