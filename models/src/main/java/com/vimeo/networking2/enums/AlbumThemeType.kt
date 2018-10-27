package com.vimeo.networking2.enums

import com.squareup.moshi.Json

/**
 * Album themes.
 */
enum class AlbumThemeType {

    /**
     * Dark theme.
     */
    @Json(name = "dark")
    DARK,

    /**
     * Standard theme.
     */
    @Json(name = "standard")
    STANDARD,

    /**
     * Unknown theme.
     */
    UNKNOWN
}
