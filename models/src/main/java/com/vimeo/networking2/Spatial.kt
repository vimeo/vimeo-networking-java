package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.SpatialProjectionType
import com.vimeo.networking2.enums.StereoFormatType

@JsonClass(generateAdapter = true)
data class Spatial(

    /**
     * 360 director timeline.
     */
    @Json(name = "director_timeline")
    val directorTimeline: List<DirectorTimeline>? = null,

    /**
     * The 360 field of view, from 30 (minimum) to 90 (maximum). The default is 50.
     */
    @Json(name = "field_of_view")
    val fieldOfView: Int? = null,

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
