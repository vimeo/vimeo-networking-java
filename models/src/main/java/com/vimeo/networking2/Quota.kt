package com.vimeo.networking2

/**
 * Quota data.
 */
data class Quota(

    /**
     * Whether you can upload HD videos.
     */
    val hd: Boolean? = null,

    /**
     * Whether you can upload SD videos.
     */
    val sd: Boolean? = null

)
