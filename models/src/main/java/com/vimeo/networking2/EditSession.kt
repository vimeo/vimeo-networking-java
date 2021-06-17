package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Information about the Vimeo Create session of a video.
 */
@JsonClass(generateAdapter = true)
data class EditSession(
    /**
     * Whether the video has licensed music.
     */
    @Json(name = "is_music_licensed")
    val isMusicLicensed: Boolean? = null,

    /**
     * Whether the current version of clip is of max resolution.
     */
    @Json(name = "is_max_resolution")
    val isMaxResolution: Boolean? = null,

    /**
     * Video session id
     */
    @Json(name = "vsid")
    val vsid: String? = null
)
