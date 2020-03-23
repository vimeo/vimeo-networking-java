package com.vimeo.networking2.common

import java.util.*

/**
 * The representation of a file that can be downloaded for playback.
 */
interface PlayableFile {

    /**
     * The direct link to the playable file.
     */
    val link: String?

    /**
     * The time in ISO 8601 format when the link to the playable file expires.
     */
    val linkExpirationTime: Date?

    /**
     * The URL for logging events.
     */
    val log: String?
}
