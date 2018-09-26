package com.vimeo.networking2

import com.vimeo.networking2.enums.VideoAction

data class VideoContext(

        /**
         * The contextual action.
         */
        val action: VideoAction?,

        /**
         * The contextual resource type.
         */
        val resourceType: String?

)
