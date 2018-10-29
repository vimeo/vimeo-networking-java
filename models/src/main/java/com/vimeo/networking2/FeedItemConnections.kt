package com.vimeo.networking2

import com.squareup.moshi.JsonClass

/**
 * All connections for a [FeedItem].
 */
@JsonClass(generateAdapter = true)
data class FeedItemConnections(

    /**
     * A list of resource URIs related to the activity.
     */
    val related: Connection? = null

)
