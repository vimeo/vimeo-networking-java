package com.vimeo.networking2.params

import com.vimeo.networking2.enums.StringValue

/**
 * The search options for the rough duration of a video.
 */
enum class SearchDurationType(override val value: String?) : StringValue {
    SHORT("short"),
    MEDIUM("medium"),
    LONG("long")
}
