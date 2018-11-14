package com.vimeo.networking2

import com.squareup.moshi.Json
import com.vimeo.networking2.enums.GroupForumsPrivacyValue
import com.vimeo.networking2.enums.GroupPrivacyValue

/**
 * Group's privacy settings.
 */
data class GroupPrivacy(

    /**
     * Who can comment on the group.
     */
    @Json(name = "comment")
    val comment: GroupPrivacyValue? = null,

    /**
     * Who is allowed to use forums related to the group.
     */
    @Json(name = "forums")
    val forums: GroupForumsPrivacyValue? = null,

    /**
     * Who can invite new members to the group.
     */
    @Json(name = "invite")
    val invite: GroupPrivacyValue? = null,

    /**
     * Who can join the group.
     */
    @Json(name = "join")
    val join: GroupPrivacyValue? = null,

    /**
     * Who can add videos to the group.
     */
    @Json(name = "videos")
    val videos: GroupPrivacyValue? = null,

    /**
     * Who can view the group.
     */
    @Json(name = "view")
    val view: GroupPrivacyValue? = null

)
