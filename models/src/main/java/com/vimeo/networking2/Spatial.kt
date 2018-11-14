package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.SpatialProjectionType
import com.vimeo.networking2.enums.StereoFormatType

@JsonClass(generateAdapter = true)
data class Spatial(

    /**
     * The 360 spatial projection.
     */
    @Json(name = "projection")
    val projection: SpatialProjectionType? = null,

    /**
     * The 360 stereo format.
     */
    @Json(name = "stereo_format")
    val stereoFormat: StereoFormatType? = null

)
