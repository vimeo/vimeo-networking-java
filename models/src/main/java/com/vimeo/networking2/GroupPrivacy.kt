@file:JvmName("GroupPrivacyUtils")

package com.vimeo.networking2

import com.vimeo.networking2.enums.GroupForumsPrivacyValue
import com.vimeo.networking2.enums.GroupPrivacyValue
import com.vimeo.networking2.enums.asEnum

/**
 * Group's privacy settings.
 */
data class GroupPrivacy(

    /**
     * Who can comment on the group.
     */
    val comment: String? = null,

    /**
     * Who is allowed to use forums related to the group.
     */
    val forums: String? = null,

    /**
     * Who can invite new members to the group.
     */
    val invite: String? = null,

    /**
     * Who can join the group.
     */
    val join: String? = null,

    /**
     * Who can add videos to the group.
     */
    val videos: String? = null,

    /**
     * Who can view the group.
     */
    val view: String? = null

)

/**
 * @see GroupPrivacy.comment
 */
val GroupPrivacy.commentType: GroupPrivacyValue
    get() = comment.asEnum(GroupPrivacyValue.UNKNOWN)

/**
 * @see GroupPrivacy.forums
 */
val GroupPrivacy.forumsType: GroupForumsPrivacyValue
    get() = forums.asEnum(GroupForumsPrivacyValue.UNKNOWN)

/**
 * @see GroupPrivacy.invite
 */
val GroupPrivacy.inviteType: GroupPrivacyValue
    get() = invite.asEnum(GroupPrivacyValue.UNKNOWN)

/**
 * @see GroupPrivacy.join
 */
val GroupPrivacy.joinType: GroupPrivacyValue
    get() = join.asEnum(GroupPrivacyValue.UNKNOWN)

/**
 * @see GroupPrivacy.videos
 */
val GroupPrivacy.videosType: GroupPrivacyValue
    get() = videos.asEnum(GroupPrivacyValue.UNKNOWN)

/**
 * @see GroupPrivacy.view
 */
val GroupPrivacy.viewType: GroupPrivacyValue
    get() = view.asEnum(GroupPrivacyValue.UNKNOWN)
