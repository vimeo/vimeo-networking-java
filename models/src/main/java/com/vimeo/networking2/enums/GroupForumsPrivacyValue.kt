package com.vimeo.networking2.enums

import com.squareup.moshi.Json

/**
 * Type of group forums.
 */
enum class GroupForumsPrivacyValue {

    /**
     * Only moderators can comment on the group.
     */
    @Json(name = "mods")
    MODS,

    /**
     * Unknown group forms privacy.
     */
    UNKNOWN

}
