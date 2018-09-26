package com.vimeo.networking2

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
