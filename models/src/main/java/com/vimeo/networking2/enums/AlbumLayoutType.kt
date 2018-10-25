package com.vimeo.networking2.enums

import com.squareup.moshi.Json

/**
 * Different type of layouts an album could be shown in.
 */
enum class AlbumLayoutType {

    /**
     * Grid layout.
     */
    @Json(name = "grid")
    GRID,

    /**
     * Player layout.
     */
    @Json(name = "player")
    PLAYER,

    /**
     * Unknown layout.
     */
    UNKNOWN
}
