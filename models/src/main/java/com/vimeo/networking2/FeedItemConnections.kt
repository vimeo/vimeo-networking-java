package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * All connections for a [FeedItem].
 *
 * @param related A list of resource URIs related to the activity.
 */
@JsonClass(generateAdapter = true)
data class FeedItemConnections(

    @Json(name = "related")
    val related: BasicConnection? = null

)
