@file:JvmName("FeedItemUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.AttributionType
import com.vimeo.networking2.enums.asEnum
import java.util.Date

/**
 * An item in the user's feed.
 *
 * @param category The category that this event occurred for. This will be present for only [AttributionType.CATEGORY]
 * feed item type.
 * @param channel The channel that this event occurred for. This will be present for only [AttributionType.CHANNEL] feed
 * item type.
 * @param group The group that this event occurred for. This will be present for only [AttributionType.GROUP] feed item
 * type.
 * @param metadata The feed item's metadata.
 * @param rawType Feed item type. See [FeedItem.type].
 * @param tag The tag that this event occurred for. This will be present for only [AttributionType.TAG] feed item type.
 * @param time Time that the event occurred.
 * @param video Video associated with this feed item.
 * @param uri The uri for the [FeedItem].
 * @param user The user that this event occurred for. This will be present for [AttributionType.LIKE],
 * [AttributionType.APPEARANCE], and [AttributionType.SHARE] activity types.
 */
@JsonClass(generateAdapter = true)
data class FeedItem(

    @Json(name = "category")
    val category: Category? = null,

    @Json(name = "channel")
    val channel: Channel? = null,

    @Json(name = "group")
    val group: Group? = null,

    @Json(name = "metadata")
    val metadata: MetadataConnections<FeedItemConnections>? = null,

    @Json(name = "type")
    val rawType: String? = null,

    @Json(name = "tag")
    val tag: Tag? = null,

    @Json(name = "time")
    val time: Date? = null,

    @Json(name = "clip")
    val video: Video? = null,

    @Json(name = "uri")
    val uri: String? = null,

    @Json(name = "user")
    val user: User? = null

)

/**
 * @see FeedItem.rawType
 * @see AttributionType
 */
val FeedItem.type: AttributionType
    get() = rawType.asEnum(AttributionType.UNKNOWN)
