package com.vimeo.networking2

data class Quota(

    /**
     * Whether you can upload HD videos.
     */
    val hd: Boolean?,

    /**
     * Whether you can upload SD videos.
     */
    val sd: Boolean?

)
