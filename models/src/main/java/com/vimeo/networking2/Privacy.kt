@file:JvmName("PrivacyUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal
import com.vimeo.networking2.enums.CommentPrivacyType
import com.vimeo.networking2.enums.EmbedPrivacyType
import com.vimeo.networking2.enums.ViewPrivacyType
import com.vimeo.networking2.enums.asEnum

/**
 * The representation of the user's video privacy settings.
 *
 * @param add Whether other users can add the user's videos.
 * @param bypassToken The token used to authenticate in playback scenarios where password entry is impossible, and the
 * user initiating playback has already entered the password.
 * @param commentPrivacy The user's privacy preference for comments. See [Privacy.commentPrivacyType].
 * @param download Whether other users can download the user's videos.
 * @param embedPrivacy The user's privacy preference for embeds. See [Privacy.embedPrivacyType].
 * @param password The password for viewing the authenticated user's videos.
 * @param viewPrivacy The privacy settings of the channel. See [Privacy.viewPrivacyType].
 * @param allowShareLink Whether link sharing is allowed.
 */
@JsonClass(generateAdapter = true)
data class Privacy(

    @Json(name = "add")
    val add: Boolean? = null,

    @Internal
    @Json(name = "_bypass_token")
    val bypassToken: String? = null,

    @Json(name = "comments")
    val commentPrivacy: String? = null,

    @Json(name = "download")
    val download: Boolean? = null,

    @Json(name = "embed")
    val embedPrivacy: String? = null,

    @Json(name = "password")
    val password: String? = null,

    @Json(name = "view")
    val viewPrivacy: String? = null,

    @Json(name = "allow_share_link")
    val allowShareLink: Boolean? = null,
)

/**
 * @see Privacy.commentPrivacy
 * @see CommentPrivacyType
 */
val Privacy.commentPrivacyType: CommentPrivacyType
    get() = commentPrivacy.asEnum(CommentPrivacyType.UNKNOWN)

/**
 * @see Privacy.embedPrivacy
 * @see EmbedPrivacyType
 */
val Privacy.embedPrivacyType: EmbedPrivacyType
    get() = embedPrivacy.asEnum(EmbedPrivacyType.UNKNOWN)

/**
 * @see Privacy.viewPrivacy
 * @see ViewPrivacyType
 */
val Privacy.viewPrivacyType: ViewPrivacyType
    get() = viewPrivacy.asEnum(ViewPrivacyType.UNKNOWN)
