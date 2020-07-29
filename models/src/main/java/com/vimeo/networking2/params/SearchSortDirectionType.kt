package com.vimeo.networking2.params

import com.vimeo.networking2.enums.StringValue

/**
 * The search options for the direction in which the search results should be sorted. Used in conjunction with the
 * [SearchSortType].
 */
enum class SearchSortDirectionType(override val value: String?) : StringValue {
    ASCENDING("asc"),
    DESCENDING("desc")
}
