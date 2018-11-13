package com.vimeo.networking2

import com.squareup.moshi.Json
import com.vimeo.networking2.common.Interaction
import com.vimeo.networking2.enums.ApiOptionsType

/**
 * Interaction with options and uri information.
 */
data class BasicInteraction(

    @Json(name = "options")
    override val options: List<ApiOptionsType>? = null,

    @Json(name = "uri")
    override val uri: String? = null

): Interaction
