@file:JvmName("LiveUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal
import com.vimeo.networking2.enums.LiveStatusType
import com.vimeo.networking2.enums.asEnum
import java.util.Date

/**
 * Live video information.
 *
 * @param activeTime The time in ISO 8601 format when the live stream began.
 * @param archivedTime The time in ISO 8601 format when the live stream was archived.
 * @param chat Information about the live clip's chat.
 * @param endedTime The time in ISO 8601 format when the live stream ended.
 * @param key The streaming key string, which is used in conjunction with the RTMP [link].
 * @param link The upstream RTMP link. Send your live content to this link.
 * @param scheduledStartTime The time in ISO 8601 format when the live stream was scheduled to start.
 * @param secondsRemaining The number of seconds before the termination of the live stream.
 * @param liveStatus The status of the RTMP [link]. See [Live.liveStatusType].
 * @param streamingError If [liveStatusType] is [LiveStatusType.STREAMING_ERROR], this is the reason for that error.
 */
@Internal
@JsonClass(generateAdapter = true)
data class Live(

    @Internal
    @Json(name = "active_time")
    val activeTime: Date? = null,

    @Internal
    @Json(name = "archived_time")
    val archivedTime: Date? = null,

    @Internal
    @Json(name = "chat")
    val chat: LiveChat? = null,

    @Internal
    @Json(name = "ended_time")
    val endedTime: Date? = null,

    @Internal
    @Json(name = "key")
    val key: String? = null,

    @Internal
    @Json(name = "link")
    val link: String? = null,

    @Internal
    @Json(name = "scheduled_start_time")
    val scheduledStartTime: Date? = null,

    @Internal
    @Json(name = "seconds_remaining")
    val secondsRemaining: Long? = null,

    @Internal
    @Json(name = "status")
    val liveStatus: String? = null,

    @Internal
    @Json(name = "streaming_error")
    val streamingError: ApiError? = null
)

/**
 * @see Live.liveStatus
 * @see LiveStatusType
 */
val Live.liveStatusType: LiveStatusType
    get() = liveStatus.asEnum(LiveStatusType.UNKNOWN)
