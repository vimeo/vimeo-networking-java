package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.CommentValue
import com.vimeo.networking2.enums.EmbedValue
import com.vimeo.networking2.enums.ViewValue

@JsonClass(generateAdapter = true)
data class Privacy(

    /**
     * Whether other users can add the user's videos.
     */
    @Json(name = "add")
    val add: Boolean? = null,

    /**
     * The user's privacy preference for comments.
     */
    @Json(name = "comments")
    val comment: CommentValue? = null,

    /**
     * Whether other users can download the user's videos.
     */
    @Json(name = "download")
    val download: Boolean? = null,

    /**
     * The user's privacy preference for embeds.
     */
    @Json(name = "embed")
    val embed: EmbedValue? = null,

    /**
     * The password for viewing the user's videos. Requires [CapabilitiesType.CAPABILITY_PROTECTED_VIDEOS].
     */
    @Json(name = "password")
    val password: String? = null,

    /**
     * The privacy settings of the channel.
     */
    @Json(name = "view")
    val view: ViewValue? = null
)
