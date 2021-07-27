package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.Entity

/**
 * A segment survey to display to the authorized user.
 *
 * @param resourceKey A globally unique identifier.
 * @param uri A uri for the survey.
 * @param title A named title.
 * @param id A named identifier.
 * @param questions A list of [SurveyQuestions][SurveyQuestion] to display to the end user.
 */
@JsonClass(generateAdapter = true)
data class UserSegmentSurvey(

    @Json(name = "resource_key")
    val resourceKey: String? = null,

    @Json(name = "uri")
    val uri: String? = null,

    @Json(name = "title")
    val title: String? = null,

    @Json(name = "id")
    val id: String? = null,

    @Json(name = "questions")
    val questions: List<SurveyQuestion>? = null


) : Entity {
    override val identifier: String? = resourceKey
}
