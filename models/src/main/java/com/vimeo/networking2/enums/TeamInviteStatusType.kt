package com.vimeo.networking2.enums

/**
 * The status of a user's team invitation.
 */
enum class TeamInviteStatusType(override val value: String?) : StringValue {

    /**
     * Team membership has been accepted.
     */
    ACCEPTED("accepted"),

    /**
     * Team membership has been offered but not yet accepted.
     */
    PENDING("pending"),

    /**
     * The team membership has unknown status.
     */
    UNKNOWN(null)
}
