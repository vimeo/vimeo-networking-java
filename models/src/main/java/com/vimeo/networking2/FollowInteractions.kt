package com.vimeo.networking2

/**
 * Follow interactions.
 */
interface FollowableInteractions<T: UpdatableInteraction?> {

    val follow: T?

}

