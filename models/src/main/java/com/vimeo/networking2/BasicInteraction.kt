package com.vimeo.networking2

import com.squareup.moshi.Json
import com.vimeo.networking2.annotations.Internal
import com.vimeo.networking2.common.Interaction
import com.vimeo.networking2.enums.ApiOptionsType

/**
 * Interaction with options and uri information.
 */
@Internal
data class BasicInteraction(

    @Internal
    @Json(name = "options")
    override val options: List<ApiOptionsType>? = null,

    @Internal
    @Json(name = "uri")
    override val uri: String? = null

): Interaction
