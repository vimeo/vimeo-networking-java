@file:JvmName("GroupPrivacyUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.vimeo.networking2.enums.GroupForumsPrivacyValue
import com.vimeo.networking2.enums.GroupPrivacyValue
import com.vimeo.networking2.enums.asEnum

/**
 * Group's privacy settings.
 */
data class GroupPrivacy(

    /**
     * Who can comment on the group.
     * @see GroupPrivacy.commentType
     */
    @Json(name = "comment")
    val comment: String? = null,

    /**
     * Who is allowed to use forums related to the group.
     * @see GroupPrivacy.forumsType
     */
    @Json(name = "forums")
    val forums: String? = null,

    /**
     * Who can invite new members to the group.
     * @see GroupPrivacy.inviteType
     */
    @Json(name = "invite")
    val invite: String? = null,

    /**
     * Who can join the group.
     * @see GroupPrivacy.joinType
     */
    @Json(name = "join")
    val join: String? = null,

    /**
     * Who can add videos to the group.
     * @see GroupPrivacy.videosType
     */
    @Json(name = "videos")
    val videos: String? = null,

    /**
     * Who can view the group.
     * @see GroupPrivacy.viewType
     */
    @Json(name = "videos")
    val view: String? = null

)

/**
 * @see GroupPrivacy.comment
 * @see GroupPrivacyValue
 */
val GroupPrivacy.commentType: GroupPrivacyValue
    get() = comment.asEnum(GroupPrivacyValue.UNKNOWN)

/**
 * @see GroupPrivacy.forums
 * @see GroupForumsPrivacyValue
 */
val GroupPrivacy.forumsType: GroupForumsPrivacyValue
    get() = forums.asEnum(GroupForumsPrivacyValue.UNKNOWN)

/**
 * @see GroupPrivacy.invite
 * @see GroupPrivacyValue
 */
val GroupPrivacy.inviteType: GroupPrivacyValue
    get() = invite.asEnum(GroupPrivacyValue.UNKNOWN)

/**
 * @see GroupPrivacy.join
 * @see GroupPrivacyValue
 */
val GroupPrivacy.joinType: GroupPrivacyValue
    get() = join.asEnum(GroupPrivacyValue.UNKNOWN)

/**
 * @see GroupPrivacy.videos
 * @see GroupPrivacyValue
 */
val GroupPrivacy.videosType: GroupPrivacyValue
    get() = videos.asEnum(GroupPrivacyValue.UNKNOWN)

/**
 * @see GroupPrivacy.view
 * @see GroupPrivacyValue
 */
val GroupPrivacy.viewType: GroupPrivacyValue
    get() = view.asEnum(GroupPrivacyValue.UNKNOWN)
