@file:JvmName("LiveUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal
import com.vimeo.networking2.enums.LiveStatusType
import com.vimeo.networking2.enums.asEnum
import java.util.*

/**
 * Live video information.
 */
@Internal
@JsonClass(generateAdapter = true)
data class Live(

    /**
     * The time in ISO 8601 format when the live stream began.
     */
    @Internal
    @Json(name = "active_time")
    val activeTime: Date? = null,

    /**
     * The time in ISO 8601 format when the live stream was archived.
     */
    @Internal
    @Json(name = "archived_time")
    val archivedTime: Date? = null,

    /**
     * Information about the live clip's chat.
     */
    @Internal
    @Json(name = "chat")
    val chat: LiveChat? = null,

    /**
     * The time in ISO 8601 format when the live stream ended.
     */
    @Internal
    @Json(name = "ended_time")
    val endedTime: Date? = null,

    /**
     * The streaming key string, which is used in conjunction with the RTMP [link].
     */
    @Internal
    @Json(name = "key")
    val key: String? = null,

    /**
     * The upstream RTMP link. Send your live content to this link.
     */
    @Internal
    @Json(name = "link")
    val link: String? = null,

    /**
     * The time in ISO 8601 format when the live stream was scheduled to start.
     */
    @Internal
    @Json(name = "scheduled_start_time")
    val scheduledStartTime: Date? = null,

    /**
     * The number of seconds before the termination of the live stream.
     */
    @Internal
    @Json(name = "seconds_remaining")
    val secondsRemaining: Date? = null,

    /**
     * The status of the RTMP [link].
     * @see Live.liveStatusType
     */
    @Internal
    @Json(name = "status")
    val liveStatus: String? = null,

    /**
     * If [liveStatusType] is [LiveStatusType.STREAMING_ERROR], this is the reason for that error.
     */
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
