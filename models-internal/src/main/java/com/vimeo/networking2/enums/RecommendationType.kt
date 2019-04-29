package com.vimeo.networking2.enums

/**
 * Type of recommendation.
 */
enum class RecommendationType(override val value: String?) : StringValue {

    CHANNEL("channel"),

    USER("user"),

    UNKNOWN(null)
}
