package com.vimeo.networking2
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * All connections for a video.
 */
@JsonClass(generateAdapter = true)
data class VideoConnections(

    /**
     * Information about the comments on this video.
     */
    @Json(name = "comment")
    val comment: Connection? = null,

    /**
     * Information about the users credited in this video.
     */
    @Json(name = "credit")
    val credit: Connection? = null,

    /**
     * Information about the users who have liked this video.
     */
    @Json(name = "likes")
    val likes: Connection? = null,

    /**
     * Information about this video's live stream stats.
     *
     * Requires [CapabilitiesType.CAPABILITY_LIVE_EVENT].
     */
    @Json(name = "live_stats")
    val liveStats: Connection? = null,

    /**
     * The video's review notes.
     *
     * Requires [CapabilitiesType.CAPABILITY_VIDEO_REVIEW].
     */
    @Json(name = "notes")
    val notes: VideoReviewConnection? = null,

    /**
     * Information about this video's thumbnails.
     */
    @Json(name = "pictures")
    val pictures: Connection? = null,

    /**
     * The DRM playback status connection for this video.
     */
    @Json(name = "playback")
    val playback: Connection? = null,

    /**
     * The recommendations for this video.
     */
    @Json(name = "recommendations")
    val recommendations: Connection? = null,

    /**
     * Related content for this video.
     */
    @Json(name = "related")
    val related: Connection? = null,

    /**
     * Information about the video's season.
     */
    @Json(name = "season")
    val season: Connection? = null,

    /**
     * Information about this video's text tracks.
     */
    @Json(name = "texttracks")
    val texttracks: Connection? = null,

    /**
     * Information about this video's VOD trailer.
     */
    @Json(name = "trailer")
    val trailer: Connection? = null,

    /**
     * Information about the user privacy of this video, if the video privacy is users.
     */
    @Json(name = "users_with_access")
    val usersWithAccess: Connection? = null,

    /**
     * Information about the versions of this video.
     *
     * Requires [CapabilitiesType.CAPABILITY_VIDEO_VERSIONS].
     */
    @Json(name = "versions")
    val versions: VideoVersionsConnection? = null

)
