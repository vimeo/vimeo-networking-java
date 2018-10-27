package com.vimeo.networking2

import com.vimeo.networking2.common.FollowableInteractions
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

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
     * When a channel appears in the context of adding or removing a video from it
     * (/videos/{video_id}/available_channels), include information about adding or removing
     * the video. This data requires a bearer token with the private scope.
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

): FollowableInteractions
