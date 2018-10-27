package com.vimeo.networking2.enums

import com.squareup.moshi.Json

enum class SpatialProjectionType {

    /**
     * The spatial projection is cubical.
     */
    @Json(name = "cubical")
    CUBICAL,

    /**
     * The spatial projection is cylindrical.
     */
    @Json(name = "cylindrical")
    CYLINDRICAL,

    /**
     * The spatial projection is dome-shaped.
     */
    @Json(name = "dome")
    DOME,

    /**
     * The spatial projection is equirectangular.
     */
    @Json(name = "equirectangular")
    EQUIRECTANGULAR,

    /**
     * The spatial projection is pyramid-shaped.
     */
    @Json(name = "pyramid")
    PYRAMID,

    /**
     * Unknown type of spatial projection.
     */
    UNKNOWN
}
