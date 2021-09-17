package com.vimeo.networking2.enums

/**
 * An enumeration of [com.vimeo.networking2.ExplorePage.rawType].
 */
enum class ExplorePageType(override val value: String?) : StringValue {
    /**
     * Represents a system page.
     */
    SYSTEM("system"),

    /**
     * A page type the networking library isn't aware of yet.
     */
    UNKNOWN("unknown")
}
