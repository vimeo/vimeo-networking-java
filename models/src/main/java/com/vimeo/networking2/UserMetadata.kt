package com.vimeo.networking2

/**
 * Created by kylevenn on 9/24/18.
 */
data class UserMetadata(

    /**
     * The list of resource URIs related to the user.
     */
    val connections: UserConnections? = null,

    /**
     * The list of interaction URIs related to the user.
     */
    val interactions: UserInteractions? = null

)
