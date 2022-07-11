package com.vimeo.networking2

import com.squareup.moshi.Json
import com.vimeo.networking2.common.Connection
import com.vimeo.networking2.enums.LiveStatusType
import com.vimeo.networking2.enums.asEnum

/**
 * The connection for a live video.
 *
 * @param status The current status of the live video.
 */
data class LiveVideoConnection(
    @Json(name = "options")
    override val options: List<String>? = null,

    @Json(name = "uri")
    override val uri: String? = null,

    @Json(name = "status")
    val status: String? = null
) : Connection

/**
 * @see LiveVideoConnection.status
 * @see LiveStatusType
 */
val LiveVideoConnection.statusType: LiveStatusType
    get() = status.asEnum(LiveStatusType.UNKNOWN)
