package com.vimeo.networking2

data class VideoConnections(

    /**
     * Information about the comments on this video.
     */
    val comment: Connection?,

    /**
     * Information about the users credited in this video.
     */
    val credit: Connection?,

    /**
     * Information about the users who have liked this video.
     */
    val likes: Connection?,

    /**
     * Information about this video's live stream stats.
     *
     * Based on CAPABILITY_LIVE_EVENT.
     */
    val liveStats: Connection?,

    /**
     * The video's review notes.
     *
     * Based on CAPABILITY_VIDEO_REVIEW.
     */
    val notes: VideoReviewConnection?,

    /**
     * Information about this video's thumbnails.
     */
    val pictures: Connection?,

    /**
     * The DRM playback status connection for this video.
     */
    val playback: Connection?,

    /**
     * The recommendations for this video.
     */
    val recommendations: Connection?,

    /**
     * Related content for this video.
     */
    val related: Connection?,

    /**
     * Information about the video's season.
     */
    val season: Connection?,

    /**
     * Information about this video's text tracks.
     */
    val texttracks: Connection?,

    /**
     * Information about this video's VOD trailer.
     */
    val trailer: Connection?,

    /**
     * Information about the user privacy of this video, if the video privacy is users.
     */
    val usersWithAccess: Connection?,

    /**
     * Information about the versions of this video.
     *
     * Based on CAPABILITY_VIDEO_VERSIONS.
     */
    val versions: VideoVersionsConnection?

)
