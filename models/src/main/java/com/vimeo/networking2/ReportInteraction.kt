package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.Interaction

/**
 * All of the interactions for a report.
 *
 * @param reason Provides the lists of valid reasons for reporting a video.
 */
@JsonClass(generateAdapter = true)
data class ReportInteraction(

    @Json(name = "options")
    override val options: List<String>? = null,

    @Json(name = "uri")
    override val uri: String? = null,

    @Json(name = "reason")
    val reason: List<String>? = null

) : Interaction
