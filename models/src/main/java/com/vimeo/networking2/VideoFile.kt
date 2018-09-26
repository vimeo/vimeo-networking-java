package com.vimeo.networking2

import java.util.*

data class VideoFile(

    /**
     * The direct link to the video file.
     */
    val link: String?,

    /**
     * The time in ISO 8601 format when the link to the video file expires.
     */
    val linkExpirationTime: Date?,

    /**
     * The URL for logging events.
     */
    val log: String?
)
