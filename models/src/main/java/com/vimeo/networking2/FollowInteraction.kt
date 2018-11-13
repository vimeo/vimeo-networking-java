package com.vimeo.networking2

import com.squareup.moshi.Json
import com.vimeo.networking2.common.UpdatableInteraction
import com.vimeo.networking2.enums.ApiOptionsType
import java.util.*

/**
 * Follow a object.
 */
data class FollowInteraction(

    @Json(name = "added")
    override val added: Boolean? = null,

    @Json(name = "added_time")
    override val addedTime: Date? = null,

    @Json(name = "options")
    override val options: List<ApiOptionsType>? = null,

    @Json(name = "uri")
    override val uri: String? = null

): UpdatableInteraction
