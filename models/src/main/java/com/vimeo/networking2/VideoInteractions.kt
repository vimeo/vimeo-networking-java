package com.vimeo.networking2
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * All action that can be taken on a video.
 */
@JsonClass(generateAdapter = true)
data class VideoInteractions(

    /**
     * The buy interaction for a On Demand video.
     */
    @Json(name = "buy")
    val buy: BuyInteraction? = null,

    /**
     * When a video is referenced by a channel URI, if the user is a moderator of the
     * channel, include information about removing the video from the channel.
     */
    @Json(name = "channel")
    val channel: Interaction? = null,

    /**
     * Information about whether the authenticated user has liked this video.
     */
    @Json(name = "like")
    val like: LikeInteraction? = null,

    /**
     * The Rent interaction for an On Demand video.
     */
    @Json(name = "rent")
    val rent: RentInteraction? = null,

    /**
     * Information about where and how to report a video.
     */
    @Json(name = "report")
    val report: Interaction? = null,

    /**
     * Subscription information for an On Demand video.
     */
    @Json(name = "subscribe")
    val subscribe: SubscriptionInteraction? = null,

    /**
     * Information about removing this video from the user's list of watched videos.
     */
    @Json(name = "watched")
    val watched: WatchedInteraction? = null,

    /**
     * Information about whether this video appears on the authenticated user's Watch Later list.
     */
    @Json(name = "watchlater")
    val watchlater: WatchLaterInteraction? = null
)
