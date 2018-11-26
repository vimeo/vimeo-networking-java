package com.vimeo.networking2.enums

/**
 * Album themes.
 */
enum class AlbumThemeType(override val value: String?) : StringValue {

    /**
     * Dark theme.
     */
    DARK("dark"),

    /**
     * Standard theme.
     */
    STANDARD("standard"),

    /**
     * Unknown theme.
     */
    UNKNOWN(null)
}
