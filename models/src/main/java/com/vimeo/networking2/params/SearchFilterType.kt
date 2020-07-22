package com.vimeo.networking2.params

import com.vimeo.networking2.enums.StringValue

/**
 * Created by anthony.restaino on 7/22/20.
 */
enum class SearchFilterType(override val value: String?) : StringValue {
    VIDEO("clip"),
    TVOD("ondemand"),
    USER("people"),
    CHANNEL("channel"),
    GROUP("group");
}
