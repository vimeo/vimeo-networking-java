@file:JvmName("PrivacyUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.CommentPrivacyType
import com.vimeo.networking2.enums.EmbedPrivacyType
import com.vimeo.networking2.enums.ViewPrivacyType
import com.vimeo.networking2.enums.asEnum

@JsonClass(generateAdapter = true)
data class Privacy(

    /**
     * Whether other users can add the user's videos.
     */
    @Json(name = "add")
    val add: Boolean? = null,

    /**
     * The user's privacy preference for comments.
     * @see Privacy.commentPrivacyType
     */
    @Json(name = "comments")
    val commentPrivacy: String? = null,

    /**
     * Whether other users can download the user's videos.
     */
    @Json(name = "download")
    val download: Boolean? = null,

    /**
     * The user's privacy preference for embeds.
     * @see Privacy.embedPrivacyType
     */
    @Json(name = "embed")
    val embedPrivacy: String? = null,

    /**
     * The privacy settings of the channel.
     * @see Privacy.viewPrivacyType
     */
    @Json(name = "view")
    val viewPrivacy: String? = null
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
