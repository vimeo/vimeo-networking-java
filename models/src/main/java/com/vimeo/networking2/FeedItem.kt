@file:JvmName("FeedItemUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.AttributionType
import com.vimeo.networking2.enums.asEnum
import java.util.*

/**
 * An item in the user's feed.
 */
@JsonClass(generateAdapter = true)
data class FeedItem(

    /**
     * The category that this event occurred for. This will be present for only
     * [AttributionType.CATEGORY] feed item type.
     */
    @Json(name = "category")
    val category: Category? = null,

    /**
     * The channel that this event occurred for. This will be present for only
     * [AttributionType.CHANNEL] feed item type.
     */
    @Json(name = "channel")
    val channel: Channel? = null,

    /**
     * The group that this event occurred for. This will be present for only
     * [AttributionType.GROUP] feed item type.
     */
    @Json(name = "group")
    val group: Group? = null,

    /**
     * The feed item's metadata.
     */
    @Json(name = "metadata")
    val metadata: MetadataConnections<FeedItemConnections>? = null,

    /**
     * The tag that this event occurred for. This will be present for only
     * [AttributionType.TAG] feed item type.
     */
    @Json(name = "tag")
    val tag: Tag? = null,

    /**
     * Time that the event occurred.
     */
    @Json(name = "time")
    val time: Date? = null,

    /**
     * Feed item type.
     * @see FeedItem.type
     */
    @Json(name = "type")
    val rawType: String? = null,

    /**
     * Video associated with ths feed item.
     */
    @Json(name = "clip")
    val video: Video? = null,

    /**
     * The uri for the [FeedItem].
     */
    @Json(name = "uri")
    val uri: String? = null,

    /**
     * The user that this event occurred for. This will be present for [AttributionType.LIKE],
     * [AttributionType.APPEARANCE], and [AttributionType.SHARE] activity types.
     */
    @Json(name = "user")
    val user: User? = null

)

/**
 * @see FeedItem.rawType
 * @see AttributionType
 */
val FeedItem.type: AttributionType
    get() = rawType.asEnum(AttributionType.UNKNOWN)
