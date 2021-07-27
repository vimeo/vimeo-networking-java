package com.vimeo.networking2.enums

/**
 * Survey question types.
 */
enum class SurveyQuestionType(override val value: String?) : StringValue {

    /**
     * The survey is expected to show multiple mutually exclusive answer choices to the user. The
     * user should only be allowed to select a single answer choice.
     */
    MULTIPLE_CHOICE_SINGLE_ANSWER("multiple_choices_single_answer"),

    /**
     * Unknown survey question type.
     */
    UNKNOWN(null)
}
