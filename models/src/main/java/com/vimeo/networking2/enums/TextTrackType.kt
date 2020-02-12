package com.vimeo.networking2.enums

/**
 * The type of text track that is being used by the video.
 */
enum class TextTrackType(override val value: String?) : StringValue {

    /**
     * The type of the text track.
     */
    CAPTIONS("captions"),

    /**
     * The text track is for subtitles.
     */
    SUBTITLES("subtitles"),

    /**
     * Unknown text track type.
     */
    UNKNOWN(null)
}
