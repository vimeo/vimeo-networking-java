package com.vimeo.networking2

/**
 * The user's download access to this On Demand video.
 */
enum class DownloadType {

    /**
     * The video is available for download.
     */
    AVAILABLE,

    /**
     * The user has purchased the video.
     */
    PURCHASED,

    /**
     * The user isn't permitted to download the video.
     */
    RESTRICTED,

    /**
     * The video isn't available for download.
     */
    UNAVAILABLE

}
