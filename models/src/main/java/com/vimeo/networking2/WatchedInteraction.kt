package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.UpdatableInteraction
import java.util.*

/**
 * All actions for the watched list for a user.
 */
@JsonClass(generateAdapter = true)
data class WatchedInteraction(

    @Json(name = "added")
    override val added: Boolean? = null,

    @Json(name = "added_time")
    override val addedTime: Date? = null,

    @Json(name = "options")
    override val options: List<String>? = null,

    @Json(name = "uri")
    override val uri: String? = null

): UpdatableInteraction
