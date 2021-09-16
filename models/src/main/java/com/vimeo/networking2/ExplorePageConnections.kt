package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * The connections of an [ExplorePage]'s [MetadataConnections]\
 *
 * @param exploreComponents This provides a connection which returns all child components of the component which has
 * this connection
 */
@JsonClass(generateAdapter = true)
data class ExplorePageConnections(
    @Json(name = "explore_components")
    val exploreComponents: BasicConnection? = null
)
