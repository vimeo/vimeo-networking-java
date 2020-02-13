package com.vimeo.networking2.enums

/**
 * The status of a video that indicates if it is playable or not.
 */
enum class VideoPlayStatusType(override val value: String?) : StringValue {

    PLAYABLE("playable"),

    PURCHASE_REQUIRED("purchase_required"),

    RESTRICTED("restricted"),

    UNAVAILABLE("unavailable"),

    UNKNOWN(null)
}
