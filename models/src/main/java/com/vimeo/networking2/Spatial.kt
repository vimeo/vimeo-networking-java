package com.vimeo.networking2

import com.vimeo.networking2.enums.SpatialProjectionType
import com.vimeo.networking2.enums.StereoFormatType

data class Spatial(

    /**
     * 360 director timeline.
     */
    val directorTimeline: List<DirectorTimeline>? = null,

    /**
     * The 360 field of view, from 30 (minimum) to 90 (maximum). The default is 50.
     */
    val fieldOfView: Int? = null,

    /**
     * The 360 spatial projection.
     */
    val projection: SpatialProjectionType = SpatialProjectionType.UNKNOWN,

    /**
     * The 360 stereo format.
     */
    val stereoFormat: StereoFormatType = StereoFormatType.UNKNOWN

)
