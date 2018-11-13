package com.vimeo.networking2

import com.vimeo.networking2.enums.TranscodeType

/**
 * The transcode information for a video upload.
 */
data class Transcode(

    /**
     * Status code for clip availability.
     */
    val status: TranscodeType? = null

)
