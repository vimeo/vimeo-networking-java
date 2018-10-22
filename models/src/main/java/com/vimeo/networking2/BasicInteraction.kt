package com.vimeo.networking2

import com.vimeo.networking2.common.Interaction
import com.vimeo.networking2.enums.ApiOptionsType

/**
 * Interaction with options and uri information.
 */
data class BasicInteraction(

    override val options: List<ApiOptionsType>? = null,

    override val uri: String? = null

): Interaction
