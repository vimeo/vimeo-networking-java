package com.vimeo.networking2.params

import com.vimeo.networking2.enums.StringValue

/**
 * Created by anthony.restaino on 7/22/20.
 */
enum class SearchDurationType(override val value: String?) : StringValue {
    SHORT("short"),
    MEDIUM("medium"),
    LONG("long")
}
