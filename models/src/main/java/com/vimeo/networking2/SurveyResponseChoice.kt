package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.Entity

/**
 * A survey response choice associated with a [SurveyQuestion].
 *
 * @param analyticsId The id that should be used when logging this [SurveyResponseChoice].
 * @param resourceKey A unique identifier for the response choice within the context of a [SurveyQuestion].
 * @param title A user-facing display title that represents the choice. This will be localized by the API.
 */
@JsonClass(generateAdapter = true)
data class SurveyResponseChoice(

    @Json(name = "analytics_id")
    val analyticsId: String? = null,

    @Json(name = "resource_key")
    val resourceKey: String? = null,

    @Json(name = "title")
    val title: String? = null

) : Entity {
    override val identifier: String? = resourceKey
}
