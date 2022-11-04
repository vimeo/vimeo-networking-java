package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal

/**
 * All connections for a video.
 *
 * @param comments Information about the comments on this video.
 * @param credit Information about the users credited in this video.
 * @param likes Information about the users who have liked this video.
 * @param liveStats Information about this video's live stream stats.
 * @param onDemand Information about the video's on-demand status.
 * @param pictures Information about this video's thumbnails.
 * @param playback Information about the DRM playback status connection for this video.
 * @param recommendations Information about the recommendations for this video.
 * @param related Information about the related content for this video.
 * @param season Information about the video's season.
 * @param textTracks Information about this video's text tracks.
 * @param trailer Information about this video's VOD trailer.
 * @param usersWithAccess Information about the user privacy of this video, if the video privacy is users.
 * @param availableAlbums Information about how to get all the logged-in user's available albums that this video can be
 * added to.
 * @param availableChannels Information about how to get all the logged-in user's available channels that this video can
 * be added to.
 * @param publish Information about how to get the Publish to Social data for this video.
 * @param teamPermissions Information about possibility sharing an individual video with team members
 */
@JsonClass(generateAdapter = true)
data class VideoConnections(

    @Json(name = "comments")
    val comments: BasicConnection? = null,

    @Json(name = "credit")
    val credit: BasicConnection? = null,

    @Json(name = "likes")
    val likes: BasicConnection? = null,

    @Internal
    @Json(name = "live_stats")
    val liveStats: BasicConnection? = null,

    @Internal
    @Json(name = "ondemand")
    val onDemand: BasicConnection? = null,

    @Json(name = "pictures")
    val pictures: BasicConnection? = null,

    @Json(name = "playback")
    val playback: BasicConnection? = null,

    @Json(name = "recommendations")
    val recommendations: BasicConnection? = null,

    @Json(name = "related")
    val related: BasicConnection? = null,

    @Json(name = "season")
    val season: VideoSeasonConnection? = null,

    @Json(name = "texttracks")
    val textTracks: BasicConnection? = null,

    @Json(name = "trailer")
    val trailer: BasicConnection? = null,

    @Json(name = "users_with_access")
    val usersWithAccess: BasicConnection? = null,

    @Json(name = "available_albums")
    val availableAlbums: BasicConnection? = null,

    @Json(name = "available_channels")
    val availableChannels: BasicConnection? = null,

    @Json(name = "publish_to_social")
    val publish: PublishJobConnection? = null,

    @Json(name = "team_permissions")
    val teamPermissions: BasicConnection? = null
)
