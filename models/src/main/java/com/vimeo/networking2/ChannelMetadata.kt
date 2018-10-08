package com.vimeo.networking2

data class ChannelMetadata(

    /**
     * A collection of information that is connected to this resource.
     */
    val connections: ChannelConnections? = null,

    /**
     * A collection of interactions for this resource.
     */
    val interactions: ChannelInteractions? = null

)
