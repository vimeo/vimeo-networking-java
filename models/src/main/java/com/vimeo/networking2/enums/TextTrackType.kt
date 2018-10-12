package com.vimeo.networking2.enums

import com.squareup.moshi.Json

enum class TextTrackType {

    /**
     * The type of the text track.
     */
    @Json(name = "captions")
    CAPTIONS,

    /**
     * The text track is for subtitles.
     */
    @Json(name = "subtitles")
    SUBTITLES
}
