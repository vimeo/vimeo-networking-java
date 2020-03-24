@file:JvmName("SpatialUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.SpatialProjectionType
import com.vimeo.networking2.enums.StereoFormatType
import com.vimeo.networking2.enums.asEnum
import java.io.Serializable

/**
 * The information used to properly project a 360 video in 3D space.
 */
@JsonClass(generateAdapter = true)
data class Spatial(

        /**
         * The 360 spatial projection.
         * @see Spatial.spatialProjectionType
         */
        @Json(name = "projection")
        val spatialProjection: String? = null,

        /**
         * The 360 stereo format.
         * @see Spatial.stereoFormatType
         */
        @Json(name = "stereo_format")
        val stereoFormat: String? = null

) : Serializable {

    companion object {
        private const val serialVersionUID = -15285318034421L
    }
}

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
