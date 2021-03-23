package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal

/**
 * All connections for a user.
 *
 * @param albums Information about the albums created by this user.
 * @param appearances Information about the appearances of this user in other videos.
 * @param block Information on the users that the current user has blocked. This data requires a
 * bearer token with the private scope.
 * @param categories Information about this user's followed categories.
 * @param channels Information about this user's subscribed channels.
 * @param connectedApps Information about this user's connected apps.
 * @param feed Information about this user's feed.
 * @param projectItemsRoot Information about the root directory containing the user's projects (folders and videos).
 * @param followers Information about the user's followers.
 * @param following Information about the users that the current user is following.
 * @param groups Information about the groups created by this user.
 * @param likes Information about the videos that this user has liked.
 * @param moderatedChannels Information about the channels that this user moderates.
 * @param notifications Information about this user's notifications. This data requires a bearer
 * token with the private scope.
 * @param pictures Information about this user's portraits.
 * @param folders Information about all the user's folders.
 * @param portfolios Information about this user's portfolios.
 * @param recommendedChannels A collection of recommended channels for the current user to follow. This data requires a
 * bearer token with the private scope.
 * @param recommendedUsers A Collection of recommended users for the current user to follow. This data requires a
 * bearer token with the private scope.
 * @param shared Information about the videos that have been shared with this user.
 * @param teamMembers Information about the user's team members.
 * @param teams Information about teams the user belongs to.
 * @param videos Information about the videos uploaded by this user.
 * @param watchLater Information about the videos that this user wants to watch later.
 */
@JsonClass(generateAdapter = true)
data class UserConnections(

    @Json(name = "albums")
    val albums: BasicConnection? = null,

    @Json(name = "appearances")
    val appearances: BasicConnection? = null,

    @Json(name = "block")
    val block: BasicConnection? = null,

    @Json(name = "categories")
    val categories: BasicConnection? = null,

    @Json(name = "channels")
    val channels: BasicConnection? = null,

    @Json(name = "connected_apps")
    val connectedApps: BasicConnection? = null,

    @Json(name = "feed")
    val feed: BasicConnection? = null,

    @Json(name = "folders_root")
    val projectItemsRoot: BasicConnection? = null,

    @Json(name = "followers")
    val followers: BasicConnection? = null,

    @Json(name = "following")
    val following: BasicConnection? = null,

    @Json(name = "groups")
    val groups: BasicConnection? = null,

    @Json(name = "likes")
    val likes: BasicConnection? = null,

    @Json(name = "moderated_channels")
    val moderatedChannels: BasicConnection? = null,

    @Internal
    @Json(name = "notifications")
    val notifications: NotificationConnection? = null,

    @Json(name = "pictures")
    val pictures: BasicConnection? = null,

    @Json(name = "projects")
    val folders: BasicConnection? = null,

    @Json(name = "portfolios")
    val portfolios: BasicConnection? = null,

    @Json(name = "recommended_channels")
    val recommendedChannels: BasicConnection? = null,

    @Json(name = "recommended_users")
    val recommendedUsers: BasicConnection? = null,

    @Json(name = "shared")
    val shared: BasicConnection? = null,

    @Json(name = "team_members")
    val teamMembers: TeamMembersConnection? = null,

    @Json(name = "teams")
    val teams: BasicConnection? = null,

    @Json(name = "videos")
    val videos: BasicConnection? = null,

    @Json(name = "watchlater")
    val watchLater: BasicConnection? = null

)
