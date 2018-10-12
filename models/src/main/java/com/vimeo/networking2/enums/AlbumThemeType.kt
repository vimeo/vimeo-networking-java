package com.vimeo.networking2.enums

import com.squareup.moshi.Json

/**
 * Album themes.
 */
enum class AlbumThemeType {

    @Json(name = "dark")
    DARK,

    @Json(name = "standard")
    STANDARD
}
