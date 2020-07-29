package com.vimeo.networking2.params

import com.vimeo.networking2.enums.StringValue

/**
 * The search options for the type of entities being searched.
 */
enum class SearchFilterType(override val value: String?) : StringValue {
    VIDEO("clip"),
    TVOD("ondemand"),
    USER("people"),
    CHANNEL("channel"),
    GROUP("group");
}
