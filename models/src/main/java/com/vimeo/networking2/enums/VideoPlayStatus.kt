package com.vimeo.networking2.enums

enum class VideoPlayStatus(override val value: String?) : StringValue {

    PLAYABLE("playable"),

    PURCHASE_REQUIRED("purchase_required"),

    RESTRICTED("restricted"),

    UNAVAILABLE("unavailable"),

    UNKNOWN(null)
}
