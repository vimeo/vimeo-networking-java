package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.TranscodeType

/**
 * The transcode information for a video upload.
 */
@JsonClass(generateAdapter = true)
data class Transcode(

    /**
     * Status code for clip availability.
     */
    @Json(name = "status")
    val status: TranscodeType? = null

)
