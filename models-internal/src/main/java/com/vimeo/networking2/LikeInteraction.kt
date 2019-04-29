package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.UpdatableInteraction
import java.util.*

/**
 * Information on liking a video.
 */
@JsonClass(generateAdapter = true)
data class LikeInteraction(

    @Json(name = "added")
    override val added: Boolean? = null,

    @Json(name = "added_time")
    override val addedTime: Date? = null,

    @Json(name = "options")
    override val options: List<String>? = null,

    @Json(name = "uri")
    override val uri: String? = null

) : UpdatableInteraction
