package com.vimeo.networking2

import com.vimeo.networking2.enums.VideoActionType
import com.vimeo.networking2.enums.VideoActionType.UNKNOWN

/**
 * Video context data.
 */
data class VideoContext(

    /**
     * The contextual action.
     */
    val action: VideoActionType = UNKNOWN,

    /**
     * The contextual resource type.
     */
    val resourceType: String? = null

)
