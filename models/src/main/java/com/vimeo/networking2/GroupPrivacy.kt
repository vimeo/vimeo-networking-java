@file:JvmName("GroupPrivacyUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.GroupForumsPrivacyType
import com.vimeo.networking2.enums.GroupPrivacyType
import com.vimeo.networking2.enums.asEnum

/**
 * Group's privacy settings.
 *
 * @param commentPrivacy Who can comment on the group. See [GroupPrivacy.commentPrivacyType].
 * @param forumsPrivacy Who is allowed to use forums related to the group. See [GroupPrivacy.forumsPrivacyType].
 * @param invitePrivacy Who can invite new members to the group. See [GroupPrivacy.invitePrivacyType].
 * @param joinPrivacy Who can join the group. See [GroupPrivacy.joinPrivacyType].
 * @param videosPrivacy Who can add videos to the group. See [GroupPrivacy.videosPrivacyType].
 * @param viewPrivacy Who can view the group. See [GroupPrivacy.viewPrivacyType].
 */
@JsonClass(generateAdapter = true)
data class GroupPrivacy(

    @Json(name = "comment")
    val commentPrivacy: String? = null,

    @Json(name = "forums")
    val forumsPrivacy: String? = null,

    @Json(name = "invite")
    val invitePrivacy: String? = null,

    @Json(name = "join")
    val joinPrivacy: String? = null,

    @Json(name = "videos")
    val videosPrivacy: String? = null,

    @Json(name = "view")
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
