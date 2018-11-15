@file:JvmName("PrivacyUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.CommentValue
import com.vimeo.networking2.enums.EmbedValue
import com.vimeo.networking2.enums.ViewValue
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
     * @see Comment.commentType
     */
    @Json(name = "comments")
    val comment: String? = null,

    /**
     * Whether other users can download the user's videos.
     */
    @Json(name = "download")
    val download: Boolean? = null,

    /**
     * The user's privacy preference for embeds.
     * @see Privacy.embedValue
     */
    @Json(name = "embed")
    val embed: String? = null,

    /**
     * The privacy settings of the channel.
     * @see Privacy.viewValue
     */
    @Json(name = "view")
    val view: String? = null
)

/**
 * @see Privacy.comment
 * @see CommentValue
 */
val Privacy.commentValue: CommentValue
    get() = comment.asEnum(CommentValue.UNKNOWN)

/**
 * @see Privacy.embed
 * @see EmbedValue
 */
val Privacy.embedValue: EmbedValue
    get() = embed.asEnum(EmbedValue.UNKNOWN)

/**
 * @see Privacy.view
 * @see ViewValue
 */
val Privacy.viewValue: ViewValue
    get() = view.asEnum(ViewValue.UNKNOWN)
