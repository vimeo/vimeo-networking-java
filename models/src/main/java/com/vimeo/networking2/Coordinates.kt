package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Relative coordinates on the surface.
 *
 * @param x The X coordinate.
 * @param y The Y coordinate.
 */
@JsonClass(generateAdapter = true)
data class Coordinates(

    @Json(name = "x")
    val x: Float = 0.5f,

    @Json(name = "y")
    val y: Float = 0.5f,
)
