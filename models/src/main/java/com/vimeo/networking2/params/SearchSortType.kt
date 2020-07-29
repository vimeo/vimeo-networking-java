package com.vimeo.networking2.params

import com.vimeo.networking2.enums.StringValue

/**
 * The search options for the type of sorting that should be applied to the results. Used in conjunction with the
 * [SearchSortDirectionType].
 */
enum class SearchSortType(override val value: String?) : StringValue {
    RELEVANCE("relevance"),
    LATEST("latest"),
    POPULARITY("popularity"),
    DURATION("duration"),
    JOIN_DATE("join_date"),
    ALPHABETICAL("alphabetical")
}
