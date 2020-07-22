package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.FollowableInteractions

/**
 * All action that can be taken on a channel.
 */
@JsonClass(generateAdapter = true)
data class ChannelInteractions(

    /**
     * An action indicating that the authenticated user is the owner of the channel and may
     * therefore add other users as channel moderators. This data requires a bearer token with
     * the private scope.
     */
    @Json(name = "add_moderators")
    val addModerators: BasicInteraction? = null,

    /**
     * An interaction that will be present in [Channel] objects returned from
     * a request to an "available_channels"connection on a video object
     * (/videos/{video_id}/available_channels). The [BasicInteraction.uri] will
     * provide the endpoint needed to complete a subsequent add/remove action to
     * add/remove the related video to/from the channel.In the event that the video
     * is not yet added to the channel, [BasicInteraction.options] will contain "PUT". In
     * the event that the video is already added to the channel, [BasicInteraction.options]
     * will contain "DELETE". This data requires a bearer token with the private scope.
     */
    @Json(name = "add_to")
    val addTo: BasicInteraction? = null,

    /**
     * An action indicating if the authenticated user has followed this channel.
     * This data requires a bearer token with the private scope.
     */
    @Json(name = "follow")
    override val follow: ChannelFollowInteraction? = null,

    /**
     * An action indicating that the authenticated user is a moderator of the channel and may
     * therefore add or remove videos from the channel. This data requires a bearer token with
     * the private scope.
     */
    @Json(name = "moderate_videos")
    val moderateVideos: BasicInteraction? = null

) : FollowableInteractions
