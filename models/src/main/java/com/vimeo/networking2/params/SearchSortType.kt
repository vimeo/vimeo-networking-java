package com.vimeo.networking2.params

import com.vimeo.networking2.enums.StringValue

/**
 * Created by anthony.restaino on 7/22/20.
 */
enum class SearchSortType(override val value: String?) : StringValue {
    RELEVANCE("relevance"),
    LATEST("latest"),
    POPULARITY("popularity"),
    DURATION("duration"),
    JOIN_DATE("join_date"),
    ALPHABETICAL("alphabetical")
}
