package com.vimeo.networking2.common

/**
 * A [VideoFile] that supports logging.
 */
interface LoggingVideoFile : VideoFile {
    /**
     * The URL for logging events.
     */
    val log: String?
}
