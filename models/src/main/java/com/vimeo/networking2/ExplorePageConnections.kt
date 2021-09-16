package com.vimeo.networking2

/**
 * The connections of an [ExplorePage]'s [MetadataConnections]\
 *
 * @param exploreComponents This provides a connection which returns all child components of the component which has
 * this connection
 */
data class ExplorePageConnections(
    val exploreComponents: BasicConnection? = null
)
