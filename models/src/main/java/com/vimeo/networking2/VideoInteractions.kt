package com.vimeo.networking2

data class VideoInteractions(

    /**
     * The Buy interaction for a On Demand video.
     */
    val buy: BuyInteraction?,

    /**
     * When a video is referenced by a channel URI, if the user is a moderator of the
     * channel, include information about removing the video from the channel.
     */
    val channel: Interaction?,

    /**
     * Information about whether the authenticated user has liked this video.
     */
    val like: LikeInteraction?,

    /**
     * The Rent interaction for an On Demand video.
     */
    val rent: RentInteraction?,

    /**
     * Information about where and how to report a video.
     */
    val report: Interaction?,

    /**
     * Subscription information for an On Demand video.
     */
    val subscribe: SubscribeInteraction?,

    /**
     * Information about removing this video from the user's list of watched videos.
     */
    val watched: WatchedInteraction?,

    /**
     * Information about whether this video appears on the authenticated user's Watch Later list.
     */
    val watchlater: WatchLaterInteraction?
)
