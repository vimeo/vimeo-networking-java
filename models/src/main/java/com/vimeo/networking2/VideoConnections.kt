package com.vimeo.networking2

/**
 * All connections for a video.
 */
data class VideoConnections(

    /**
     * Information about the comments on this video.
     */
    val comment: Connection? = null,

    /**
     * Information about the users credited in this video.
     */
    val credit: Connection? = null,

    /**
     * Information about the users who have liked this video.
     */
    val likes: Connection? = null,

    /**
     * Information about this video's live stream stats.
     *
     * Requires [CapabilitiesType.CAPABILITY_LIVE_EVENT].
     */
    val liveStats: Connection? = null,

    /**
     * The video's review notes.
     *
     * Requires [CapabilitiesType.CAPABILITY_VIDEO_REVIEW].
     */
    val notes: VideoReviewConnection? = null,

    /**
     * Information about this video's thumbnails.
     */
    val pictures: Connection? = null,

    /**
     * The DRM playback status connection for this video.
     */
    val playback: Connection? = null,

    /**
     * The recommendations for this video.
     */
    val recommendations: Connection? = null,

    /**
     * Related content for this video.
     */
    val related: Connection? = null,

    /**
     * Information about the video's season.
     */
    val season: Connection? = null,

    /**
     * Information about this video's text tracks.
     */
    val textTracks: Connection? = null,

    /**
     * Information about this video's VOD trailer.
     */
    val trailer: Connection? = null,

    /**
     * Information about the user privacy of this video, if the video privacy is users.
     */
    val usersWithAccess: Connection? = null,

    /**
     * Information about the versions of this video.
     *
     * Requires [CapabilitiesType.CAPABILITY_VIDEO_VERSIONS].
     */
    val versions: VideoVersionsConnection? = null

)
