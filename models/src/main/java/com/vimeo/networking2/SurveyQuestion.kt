package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.Entity
import com.vimeo.networking2.enums.SurveyQuestionType
import com.vimeo.networking2.enums.asEnum

/**
 * A representation of a user-facing survey question. Questions are expected to be presented to a user with
 * a series of multiple-choice responses to select as an answer.
 *
 * @param resourceKey A globally unique identifier.
 * @param emojiTitle A unicode emoji character in “surrogate pair” representation (e.g. \uD83D\uDC4B).
 * @param question The survey question that the user should be asked. This string will be localized.
 * @param rawType The type of the survey question. See [SurveyQuestion.type].
 * @param responseChoices A list of [SurveyResponseChoices][SurveyResponseChoice] to present to a user as valid
 * answers to the question. The order of this list will be randomized by the Vimeo api.
 *
 */
@JsonClass(generateAdapter = true)
data class SurveyQuestion(

    @Json(name = "resource_key")
    val resourceKey: String? = null,

    @Json(name = "emoji_title")
    val emojiTitle: String? = null,

    @Json(name = "title")
    val question: String? = null,

    @Json(name = "type")
    val rawType: String? = null,

    @Json(name = "answers")
    val responseChoices: List<SurveyResponseChoice>? = null

) : Entity {
    override val identifier: String? = resourceKey
}

/**
 * @see SurveyQuestionType
 * @see SurveyQuestion.rawType
 */
val SurveyQuestion.type: SurveyQuestionType
    get() = rawType.asEnum(SurveyQuestionType.UNKNOWN)
