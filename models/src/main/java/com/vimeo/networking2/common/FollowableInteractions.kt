package com.vimeo.networking2.common

/**
 * Defines a collection of interactions that contains a [follow] interaction of
 * type [UpdatableInteraction].
 */
interface FollowableInteractions {

    /**
     * An [UpdatableInteraction] that contains information on how
     * to follow an entity.
     */
    val follow: UpdatableInteraction?

}
