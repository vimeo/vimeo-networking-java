package com.vimeo.networking2

import java.util.*

/**
 * [TVodItem] publish information.
 */
data class Publish(

    /**
     * Whether the [TVodItem] has been published
     */
    val enabled: Boolean? = null,

    /**
     * The time in IS 8601 format when this [TVodItem] was published.
     */
    val time: Date? = null
)
