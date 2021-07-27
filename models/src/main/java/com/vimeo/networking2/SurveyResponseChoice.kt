package com.vimeo.networking2

import com.squareup.moshi.Json
import com.vimeo.networking2.common.Entity

/**
 * A survey response choice associated with a [SurveyQuestion].
 *
 * @param id A unique identifier for the response choice within the context of a [SurveyQuestion].
 * @param title A user-facing display title that represents the choice. This will be localized by the API.
 */
data class SurveyResponseChoice(

    @Json(name = "id")
    val id: String? = null,

    @Json(name = "title")
    val title: String? = null,

    ) : Entity {
    override val identifier: String? = id
}
