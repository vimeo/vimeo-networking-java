package com.vimeo.networking2.enums

/**
 * Type of TVOD item.
 */
enum class TvodItemType(override val value: String?) : StringValue {

    /**
     * The TVOD page is for a film.
     */
    FILM("film"),

    /**
     * The TVOD page is for a series
     */
    SERIES("series"),

    UNKNOWN(null)
}
