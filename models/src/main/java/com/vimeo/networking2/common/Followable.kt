package com.vimeo.networking2.common

import com.vimeo.networking2.Metadata

/**
 * Represents a data model that can be followed by the user. Provides access to a [Metadata] property that contains
 * [FollowableInteractions].
 */
interface Followable {

    /**
     * The metadata for the [Entity] that contains a set of [FollowableInteractions].
     */
    val metadata: Metadata<*, out FollowableInteractions>?

}
