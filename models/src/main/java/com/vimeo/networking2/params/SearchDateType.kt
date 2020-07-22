package com.vimeo.networking2.params

import com.vimeo.networking2.enums.StringValue

/**
 * Created by anthony.restaino on 7/22/20.
 */
enum class SearchDateType(override val value: String?) : StringValue {
    TODAY("today"),
    THIS_WEEK("this-week"),
    THIS_MONTH("this-month"),
    THIS_YEAR("this-year")
}
