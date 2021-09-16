package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * The connections of an [ExploreComponent]'s [MetadataConnections].
 *
 * @param exploreComponents This provides a connection which returns all child components of the component which has
 * this connection
 * @param parent This provides a connection to the parent of the component which has this connection
 */
@JsonClass(generateAdapter = true)
data class ExploreComponentConnections(
    @Json(name = "explore_components")
    val exploreComponents: BasicConnection,
    @Json(name = "parent")
    val parent: BasicConnection
)
