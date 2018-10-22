package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.TextTrackType

/**
 * Information on a text text.
 */
@JsonClass(generateAdapter = true)
data class TextTrack(

    /**
     * Whether this text track is active.
     */
    @Json(name = "active")
    val active: Boolean? = null,

    /**
     * The read-only URL of the text track file, intended for use with HLS playback.
     */
    @Json(name = "hsl_link")
    val hlsLink: String? = null,

    /**
     * The language code for this text track. To see a full list, request
     * `/languages?filter=texttrack`.
     */
    @Json(name = "language")
    val language: String? = null,

    /**
     * The read-only URL of the text track file. You can upload to this link when you
     * create it for the first time.
     */
    @Json(name = "link")
    val link: String? = null,

    /**
     * The relative URI of the text track.
     */
    @Json(name = "uri")
    val uri: String? = null,

    /**
     * The descriptive name of this text track.
     */
    @Json(name = "name")
    val name: String? = null,

    /**
     *The type of the text track.
     */
    @Json(name = "type")
    val type: TextTrackType = TextTrackType.UNKNOWN

)
