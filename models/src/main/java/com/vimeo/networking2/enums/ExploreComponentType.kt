package com.vimeo.networking2.enums

/**
 * An enumeration of [com.vimeo.networking2.ExploreComponent.rawType]
 */
enum class ExploreComponentType(override val value: String?) : StringValue {
    /**
     * A component type indicating the component represents a carousel
     */
    CAROUSEL("carousel"),
    /**
     * A component type indicating the component represents a standard list of content
     */
    STANDARD_MULTI("standard_multi"),

    /**
     * A component type the networking library isn't aware of yet
     */
    UNKNOWN("unknown")
}
