package com.vimeo.networking2

data class ChannelInteractions(

    /**
     * An action indicating that the authenticated user is the owner of the channel and may
     * therefore add other users as channel moderators. This data requires a bearer token with
     * the private scope.
     */
    val addModerators: Interaction?,

    /**
     * When a channel appears in the context of adding or removing a video from it
     * (/videos/{video_id}/available_channels), include information about adding or removing
     * the video. This data requires a bearer token with the private scope.
     */
    val addTo: Interaction?,

    /**
     * An action indicating if the authenticated user has followed this channel.
     * This data requires a bearer token with the private scope.
     */
    val follow: ChannelFollowInteraction?,

    /**
     * An action indicating that the authenticated user is a moderator of the channel and may
     * therefore add or remove videos from the channel. This data requires a bearer token with
     * the private scope.
     */
    val moderateVideos: Interaction?

)
