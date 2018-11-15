@file:JvmName("SpatialUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.SpatialProjectionType
import com.vimeo.networking2.enums.StereoFormatType
import com.vimeo.networking2.enums.asEnum

@JsonClass(generateAdapter = true)
data class Spatial(

    /**
     * The 360 spatial projection.
     * @see Spatial.projectionType
     */
    @Json(name = "projection")
    val projection: String? = null,

    /**
     * The 360 stereo format.
     * @see Spatial.stereoFormatType
     */
    @Json(name = "stereo_format")
    val stereoFormat: String? = null

)

/**
 * @see Spatial.projection
 * @see SpatialProjectionType
 */
val Spatial.projectionType: SpatialProjectionType
    get() = projection.asEnum(SpatialProjectionType.UNKNOWN)

/**
 * @see Spatial.stereoFormat
 * @see StereoFormatType
 */
val Spatial.stereoFormatType: StereoFormatType
    get() = stereoFormat.asEnum(StereoFormatType.UNKNOWN)
