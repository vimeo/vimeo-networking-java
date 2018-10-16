package com.vimeo.networking2

/**
 * Video badges data.
 */
data class VideoBadges(

    /**
     * Whether the video has an HDR-compatible transcode.
     */
    val hdr: Boolean? = null,

    /**
     * Live data.
     */
    val live: Live? = null,

    /**
     * Whether the video is a Vimeo Weekend Challenge.
     */
    val weekendChallenge: Boolean? = null
)
