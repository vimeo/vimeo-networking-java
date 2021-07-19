@file:JvmName("TextTrackUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.TextTrackType
import com.vimeo.networking2.enums.asEnum
import java.util.Date

/**
 * Information on a text text.
 *
 * @param active Whether this text track is active.
 * @param hlsLink The read-only URL of the text track file, intended for use with HLS playback.
 * @param hlsLinkExpiresTime The time in ISO 8601 format when the read-only HLS playback text track file expires.
 * @param language The language code for this text track. To see a full list, request `/languages?filter=texttrack`.
 * @param link The read-only URL of the text track file. You can upload to this link when you create it for the first
 * time.
 * @param uri The relative URI of the text track.
 * @param name The descriptive name of this text track.
 * @param rawType The type of the text track. See [TextTrack.type].
 */
@JsonClass(generateAdapter = true)
data class TextTrack(

    @Json(name = "active")
    val active: Boolean? = null,

    @Json(name = "hsl_link")
    val hlsLink: String? = null,

    val hlsLinkExpiresTime: Date? = null,

    @Json(name = "language")
    val language: String? = null,

    @Json(name = "link")
    val link: String? = null,

    @Json(name = "uri")
    val uri: String? = null,

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "type")
    val rawType: String? = null

)

/**
 * @see TextTrack.rawType
 * @see TextTrackType
 */
val TextTrack.type: TextTrackType
    get() = rawType.asEnum(TextTrackType.UNKNOWN)
