package com.vimeo.networking2.params

import com.vimeo.networking2.enums.StringValue

/**
 * The search options for the time range in which a video was uploaded.
 */
enum class SearchDateType(override val value: String?) : StringValue {
    TODAY("today"),
    THIS_WEEK("this-week"),
    THIS_MONTH("this-month"),
    THIS_YEAR("this-year")
}
