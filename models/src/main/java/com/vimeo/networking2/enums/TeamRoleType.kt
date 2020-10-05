package com.vimeo.networking2.enums

/**
 * The user's role on a team. Roles have different permissions levels.
 */
enum class TeamRoleType(override val value: String?) : StringValue {

    /**
     * The team member has administrator permissions.
     */
    ADMIN("Admin"),

    /**
     * The team member has contributor permissions.
     */
    CONTRIBUTOR("Contributor"),

    /**
     * The team member has owner permissions.
     */
    OWNER("Owner"),

    /**
     * The team member has uploader permissions.
     */
    UPLOADER("Uploader"),

    /**
     * The team member has viewer permissions.
     */
    VIEWER("Viewer"),

    /**
     * The team member has unknown permissions.
     */
    UNKNOWN(null)
}
