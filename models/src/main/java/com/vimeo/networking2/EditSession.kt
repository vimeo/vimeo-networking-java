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
    val isMusicLicensed: Boolean? = null
)
