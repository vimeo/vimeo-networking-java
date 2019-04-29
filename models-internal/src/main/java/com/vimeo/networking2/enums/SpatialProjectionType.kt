package com.vimeo.networking2.enums

enum class SpatialProjectionType(override val value: String?) : StringValue {

    /**
     * The spatial projection is cubical.
     */
    CUBICAL("cubical"),

    /**
     * The spatial projection is cylindrical.
     */
    CYLINDRICAL("cylindrical"),

    /**
     * The spatial projection is dome-shaped.
     */
    DOME("dome"),

    /**
     * The spatial projection is equirectangular.
     */
    EQUIRECTANGULAR("equirectangular"),

    /**
     * The spatial projection is pyramid-shaped.
     */
    PYRAMID("pyramid"),

    /**
     * Unknown type of spatial projection.
     */
    UNKNOWN(null)
}
