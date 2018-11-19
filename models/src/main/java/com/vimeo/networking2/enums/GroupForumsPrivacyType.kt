package com.vimeo.networking2.enums

/**
 * Type of group forums.
 */
enum class GroupForumsPrivacyType(override val value: String?) : StringValue {

    /**
     * Only moderators can comment on the group.
     */
    MODS("mods"),

    /**
     * Unknown group forms privacy.
     */
    UNKNOWN(null)

}
