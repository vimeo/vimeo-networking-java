@file:JvmName("SpatialUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.SpatialProjectionType
import com.vimeo.networking2.enums.StereoFormatType
import com.vimeo.networking2.enums.asEnum

/**
 * The information used to properly project a 360 video in 3D space.
 *
 * @param spatialProjection The 360 spatial projection. See [Spatial.spatialProjectionType].
 * @param stereoFormat The 360 stereo format. See [Spatial.stereoFormatType].
 */
@JsonClass(generateAdapter = true)
data class Spatial(

    @Json(name = "projection")
    val spatialProjection: String? = null,

    @Json(name = "stereo_format")
    val stereoFormat: String? = null

)

/**
 * @see Spatial.spatialProjection
 * @see SpatialProjectionType
 */
val Spatial.spatialProjectionType: SpatialProjectionType
    get() = spatialProjection.asEnum(SpatialProjectionType.UNKNOWN)

/**
 * @see Spatial.stereoFormat
 * @see StereoFormatType
 */
val Spatial.stereoFormatType: StereoFormatType
    get() = stereoFormat.asEnum(StereoFormatType.UNKNOWN)
