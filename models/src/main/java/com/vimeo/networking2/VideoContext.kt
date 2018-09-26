package com.vimeo.networking2

import com.vimeo.networking2.enums.VideoActionType

data class VideoContext(

    /**
     * The contextual action.
     */
    val action: VideoActionType?,

    /**
     * The contextual resource type.
     */
    val resourceType: String?

)
