@file:JvmName("GroupPrivacyUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.GroupForumsPrivacyType
import com.vimeo.networking2.enums.GroupPrivacyType
import com.vimeo.networking2.enums.asEnum

/**
 * Group's privacy settings.
 */
@JsonClass(generateAdapter = true)
data class GroupPrivacy(

    /**
     * Who can comment on the group.
     * @see GroupPrivacy.commentPrivacyType
     */
    @Json(name = "comment")
    val commentPrivacy: String? = null,

    /**
     * Who is allowed to use forums related to the group.
     * @see GroupPrivacy.forumsPrivacyType
     */
    @Json(name = "forums")
    val forumsPrivacy: String? = null,

    /**
     * Who can invite new members to the group.
     * @see GroupPrivacy.invitePrivacyType
     */
    @Json(name = "invite")
    val invitePrivacy: String? = null,

    /**
     * Who can join the group.
     * @see GroupPrivacy.joinPrivacyType
     */
    @Json(name = "join")
    val joinPrivacy: String? = null,

    /**
     * Who can add videos to the group.
     * @see GroupPrivacy.videosPrivacyType
     */
    @Json(name = "videos")
    val videosPrivacy: String? = null,

    /**
     * Who can view the group.
     * @see GroupPrivacy.viewPrivacyType
     */
    @Json(name = "videos")
    val viewPrivacy: String? = null

)

/**
 * @see GroupPrivacy.commentPrivacy
 * @see GroupPrivacyType
 */
val GroupPrivacy.commentPrivacyType: GroupPrivacyType
    get() = commentPrivacy.asEnum(GroupPrivacyType.UNKNOWN)

/**
 * @see GroupPrivacy.forumsPrivacy
 * @see GroupForumsPrivacyType
 */
val GroupPrivacy.forumsPrivacyType: GroupForumsPrivacyType
    get() = forumsPrivacy.asEnum(GroupForumsPrivacyType.UNKNOWN)

/**
 * @see GroupPrivacy.invitePrivacy
 * @see GroupPrivacyType
 */
val GroupPrivacy.invitePrivacyType: GroupPrivacyType
    get() = invitePrivacy.asEnum(GroupPrivacyType.UNKNOWN)

/**
 * @see GroupPrivacy.joinPrivacy
 * @see GroupPrivacyType
 */
val GroupPrivacy.joinPrivacyType: GroupPrivacyType
    get() = joinPrivacy.asEnum(GroupPrivacyType.UNKNOWN)

/**
 * @see GroupPrivacy.videosPrivacy
 * @see GroupPrivacyType
 */
val GroupPrivacy.videosPrivacyType: GroupPrivacyType
    get() = videosPrivacy.asEnum(GroupPrivacyType.UNKNOWN)

/**
 * @see GroupPrivacy.viewPrivacy
 * @see GroupPrivacyType
 */
val GroupPrivacy.viewPrivacyType: GroupPrivacyType
    get() = viewPrivacy.asEnum(GroupPrivacyType.UNKNOWN)
