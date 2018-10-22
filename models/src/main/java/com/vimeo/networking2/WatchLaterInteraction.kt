package com.vimeo.networking2

import com.vimeo.networking2.common.UpdatableInteraction
import com.vimeo.networking2.enums.ApiOptionsType
import java.util.*

/**
 * All actions for watch later.
 */
data class WatchLaterInteraction(

    override val added: Boolean? = null,

    override val addedTime: Date? = null,

    override val options: List<ApiOptionsType>? = null,

    override val uri: String? = null

): UpdatableInteraction
