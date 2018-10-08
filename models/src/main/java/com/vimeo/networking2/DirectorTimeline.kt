package com.vimeo.networking2

data class DirectorTimeline(

    /**
     * The director timeline pitch, from -90 (minimum) to 90 (maximum).
     */
    val pitch: Int? = null,

    /**
     * The director timeline roll.
     */
    val roll: Int? = null,

    /**
     * The director timeline time code.
     */
    val timeCode: Int? = null,

    /**
     * The director timeline yaw, from 0 (minimum) to 360 (maximum).
     */
    val yaw: Int? = null

)
