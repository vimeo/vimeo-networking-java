package com.vimeo.networking2

import com.vimeo.networking2.enums.GroupForumsPrivacyValue
import com.vimeo.networking2.enums.GroupPrivacyValue

/**
 * Group's privacy settings.
 */
data class GroupPrivacy(

    /**
     * Who can comment on the group.
     */
    val comment: GroupPrivacyValue? = null,

    /**
     * Who is allowed to use forums related to the group.
     */
    val forums: GroupForumsPrivacyValue? = null,

    /**
     * Who can invite new members to the group.
     */
    val invite: GroupPrivacyValue? = null,

    /**
     * Who can join the group.
     */
    val join: GroupPrivacyValue? = null,

    /**
     * Who can add videos to the group.
     */
    val videos: GroupPrivacyValue? = null,

    /**
     * Who can view the group.
     */
    val view: GroupPrivacyValue? = null

)
