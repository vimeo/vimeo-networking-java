package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal

/**
 * All connections for a video.
 */
@JsonClass(generateAdapter = true)
data class VideoConnections(

    /**
     * Information about the comments on this video.
     */
    @Json(name = "comments")
    val comments: BasicConnection? = null,

    /**
     * Information about the users credited in this video.
     */
    @Json(name = "credit")
    val credit: BasicConnection? = null,

    /**
     * Information about the users who have liked this video.
     */
    @Json(name = "likes")
    val likes: BasicConnection? = null,

    /**
     * Information about this video's live stream stats.
     */
    @Internal
    @Json(name = "live_stats")
    val liveStats: BasicConnection? = null,

    /**
     * Information about the video's on-demand status.
     */
    @Internal
    @Json(name = "ondemand")
    val onDemand: BasicConnection? = null,

    /**
     * Information about this video's thumbnails.
     */
    @Json(name = "pictures")
    val pictures: BasicConnection? = null,

    /**
     * The DRM playback status connection for this video.
     */
    @Json(name = "playback")
    val playback: BasicConnection? = null,

    /**
     * The recommendations for this video.
     */
    @Json(name = "recommendations")
    val recommendations: BasicConnection? = null,

    /**
     * Related content for this video.
     */
    @Json(name = "related")
    val related: BasicConnection? = null,

    /**
     * Information about the video's season.
     */
    @Json(name = "season")
    val season: VideoSeasonConnection? = null,

    /**
     * Information about this video's text tracks.
     */
    @Json(name = "texttracks")
    val textTracks: BasicConnection? = null,

    /**
     * Information about this video's VOD trailer.
     */
    @Json(name = "trailer")
    val trailer: BasicConnection? = null,

    /**
     * Information about the user privacy of this video, if the video privacy is users.
     */
    @Json(name = "users_with_access")
    val usersWithAccess: BasicConnection? = null,

    /**
     * Connection to get all the logged-in user's available albums that this video can be added to.
     */
    @Json(name = "available_albums")
    val availableAlbums: BasicConnection? = null,

    /**
     * Connection to get all the logged-in user's available channels that this video can be added to.
     */
    @Json(name = "available_channels")
    val availableChannels: BasicConnection? = null,

    /**
     * Connection to get the Publish to Social data for this video.
     */
    @Json(name = "publish_to_social")
    val publish: PublishJobConnection? = null
)
