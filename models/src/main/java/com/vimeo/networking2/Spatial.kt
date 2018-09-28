package com.vimeo.networking2

import com.vimeo.networking2.enums.SpatialProjectionType
import com.vimeo.networking2.enums.StereoFormatType

data class Spatial(

    val directorTimeline: List<DirectorTimeline>?,

    /**
     * The 360 field of view, from 30 (minimum) to 90 (maximum). The default is 50.
     */
    val fieldOfView: Int?,

    /**
     * The 360 spatial projection.
     */
    val projection: SpatialProjectionType?,

    /**
     * The 360 stereo format.
     */
    val stereo_format: StereoFormatType?

)
