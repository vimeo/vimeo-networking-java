package com.vimeo.networking2

data class VideoBadges(

    /**
     * Whether the video has an HDR-compatible transcode.
     */
    val hdr: Boolean? = null,

    /**
     * Live.
     */
    val live: Live? = null,

    /**
     * Whether the video is a Vimeo Weekend Challenge.
     */
    val weekendChallenge: Boolean? = null
)
