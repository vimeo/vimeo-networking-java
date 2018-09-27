package com.vimeo.networking2

import java.util.*

data class Periodic(

    /**
     * The number of bytes remaining in your upload quota for the current period.
     */
    val free: Long?,

    /**
     * The total number of bytes that you can upload per period.
     */
    val max: Long?,

    /**
     * The time in ISO 8601 format when your upload quota resets.
     */
    val resetDate: Date?,

    /**
     * The number of bytes that you've already uploaded against your quota in the current period.
     */
    val used: Long?

)
