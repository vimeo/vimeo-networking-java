package com.vimeo.networking2

/**
 * The connections of an [ExploreComponent]'s [MetadataConnections].
 *
 * @param exploreComponents This provides a connection which returns all child components of the component which has
 * this connection
 * @param parent This provides a connection to the parent of the component which has this connection
 */
data class ExploreComponentConnections(
    val exploreComponents: BasicConnection,
    val parent: BasicConnection
)
