package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal

/**
 * All action that can be taken on a video.
 */
@JsonClass(generateAdapter = true)
data class VideoInteractions(

    /**
     * The buy interaction for a On Demand video.
     */
    @Internal
    @Json(name = "buy")
    val buy: BuyInteraction? = null,

    /**
     * When a video is referenced by a channel URI, if the user is a moderator of the
     * channel, include information about removing the video from the channel.
     */
    @Internal
    @Json(name = "channel")
    val channel: BasicInteraction? = null,

    /**
     * Information about whether the authenticated user has liked this video.
     */
    @Json(name = "like")
    val like: LikeInteraction? = null,

    /**
     * The Rent interaction for an On Demand video.
     */
    @Internal
    @Json(name = "rent")
    val rent: RentInteraction? = null,

    /**
     * Information about where and how to report a video.
     */
    @Json(name = "report")
    val report: BasicInteraction? = null,

    /**
     * Subscription information for an On Demand video.
     */
    @Internal
    @Json(name = "subscribe")
    val subscription: SubscriptionInteraction? = null,

    /**
     * Information about whether this video appears on the authenticated user's Watch Later list.
     */
    @Json(name = "watchlater")
    val watchLater: WatchLaterInteraction? = null

)
