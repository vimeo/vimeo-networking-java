package com.vimeo.networking2.params

import com.vimeo.networking2.enums.StringValue

/**
 * Created by anthony.restaino on 7/22/20.
 */
enum class SearchFacetType(override val value: String?) : StringValue {
    TYPE("type"),
    CATEGORY("category"),
    DURATION("duration"),
    LICENSE("license"),
    UPLOADED("uploaded"),
    USER_TYPE("user_type")
}
