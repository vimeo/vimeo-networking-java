package com.vimeo.networking2.enums

/**
 * Representation of the type of Season
 */
enum class SeasonType(override val value: String?) : StringValue {

    /**
     * A main content group.
     */
    MAIN("main"),

    /**
     * A bonus content group.
     */
    EXTRAS("extras"),

    UNKNOWN(null)

}
