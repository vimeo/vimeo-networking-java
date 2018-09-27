package com.vimeo.networking2

data class Lifetime(

    /**
     * The number of bytes remaining in your lifetime maximum.
     */
    val free: Long?,

    /**
     * The total number of bytes that you can upload across the lifetime of your account.
     */
    val max: Long?,

    /**
     * The number of bytes that you've already uploaded against your lifetime limit.
     */
    val used: Long?
)
