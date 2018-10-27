package com.vimeo.networking2

import com.vimeo.networking2.common.UpdatableInteraction
import com.vimeo.networking2.enums.ApiOptionsType
import java.util.*

/**
 * Follow a object.
 */
data class FollowInteraction(

    override val added: Boolean? = null,

    override val addedTime: Date? = null,

    override val options: List<ApiOptionsType>? = null,

    override val uri: String? = null

): UpdatableInteraction
