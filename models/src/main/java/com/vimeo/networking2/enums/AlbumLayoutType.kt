package com.vimeo.networking2.enums

import com.squareup.moshi.Json

/**
 * The album's layout preference.
 */
enum class AlbumLayoutType {

    @Json(name = "grid")
    GRID,

    @Json(name = "player")
    PLAYER
}
