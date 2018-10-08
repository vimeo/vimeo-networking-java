package com.vimeo.networking2

data class VideoMetadata(

    /**
     * A list of resource URIs related to the video
     */
    val connections: VideoConnections? = null,

    /**
     * A list of interaction URIs related to the video
     */
    val interactions: VideoInteractions? = null

)
