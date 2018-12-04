package com.vimeo.networking2.enums

/**
 * Status types for a user's trial.
 */
enum class TrialType(override val value: String?) : StringValue {

    /**
     * Unknown trial type.
     */
    UNKNOWN(null),

    /**
     * The user is currently in a free trial.
     */
    FREE_TRIAL("free_trial")
}
