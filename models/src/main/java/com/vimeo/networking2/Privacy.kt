package com.vimeo.networking2

import com.vimeo.networking2.enums.CommentValue
import com.vimeo.networking2.enums.EmbedValue
import com.vimeo.networking2.enums.ViewValue

data class Privacy(

    /**
     * Whether other users can add the user's videos.
     */
    val add: Boolean,

    /**
     * The user's privacy preference for comments.
     */
    val comment: CommentValue?,

    /**
     * Whether other users can download the user's videos.
     */
    val download: Boolean?,

    /**
     * The user's privacy preference for embeds.
     */
    val embed: EmbedValue?,

    /**
     * The password for viewing the user's videos. Based on CAPABILITY_PROTECTED_VIDEOS.
     */
    val password: String?,

    /**
     * The privacy settings of the channel.
     */
    val view: ViewValue?
)
