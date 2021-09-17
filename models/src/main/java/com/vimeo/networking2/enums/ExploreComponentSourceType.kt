package com.vimeo.networking2.enums

/**
 * An enumeration of [com.vimeo.networking2.ExploreComponentSource.rawType].
 */
enum class ExploreComponentSourceType(override val value: String?) : StringValue {
    /**
     * Source type indicating that [com.vimeo.networking2.Video] should be returned as a component source.
     */
    VIDEO("video"),

    /**
     * Source type indicating that [com.vimeo.networking2.Album] should be returned as a component source.
     */
    ALBUM("album"),

    /**
     * Source type indicating that [com.vimeo.networking2.Channel] should be returned as a component source.
     */
    CHANNEL("channel"),

    /**
     * Some sort of source type was returned the networking library is unaware of.
     */
    UNKNOWN("unknown")
}
