package com.vimeo.networking2.enums

/**
 * Different type of layouts an album could be shown in.
 */
enum class AlbumLayoutType(override val value: String?) : StringValue {

    /**
     * Grid layout.
     */
    GRID("grid"),

    /**
     * Player layout.
     */
    PLAYER("player"),

    /**
     * Unknown layout.
     */
    UNKNOWN(null)
}
