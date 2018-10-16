package com.vimeo.networking2

import com.vimeo.networking2.enums.TextTrackType
import java.util.*

/**
 * Information on a text text.
 */
data class TextTrack(

    /**
     * Whether this text track is active.
     */
    val active: Boolean? = null,

    /**
     * The read-only URL of the text track file, intended for use with HLS playback.
     */
    val hlsLink: String? = null,

    /**
     * The time in ISO 8601 format when the read-only HLS playback text track file expires.
     */
    val hlsLinkExpiresTime: Date? = null,

    /**
     * The language code for this text track. To see a full list, request
     * `/languages?filter=texttrack`.
     */
    val language: String? = null,

    /**
     * The read-only URL of the text track file. You can upload to this link when you
     * create it for the first time.
     */
    val link: String? = null,

    /**
     * The time in ISO 8601 format when the text track link expires.
     */
    val linkExpiresTime: Date? = null,

    /**
     * The relative URI of the text track.
     */
    val uri: String? = null,

    /**
     * The descriptive name of this text track.
     */
    val name: String? = null,

    /**
     *The type of the text track.
     */
    val type: TextTrackType = TextTrackType.UNKNOWN

)
