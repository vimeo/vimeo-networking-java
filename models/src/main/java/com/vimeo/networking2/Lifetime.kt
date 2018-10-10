package com.vimeo.networking2

/**
 * Lifetime data.
 */
data class Lifetime(

    /**
     * The number of bytes remaining in your lifetime maximum.
     */
    val free: Long? = null,

    /**
     * The total number of bytes that you can upload across the lifetime of your account.
     */
    val max: Long? = null,

    /**
     * The number of bytes that you've already uploaded against your lifetime limit.
     */
    val used: Long? = null
)
