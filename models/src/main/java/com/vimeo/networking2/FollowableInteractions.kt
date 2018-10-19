package com.vimeo.networking2

/**
 * Followable interactions.
 */
interface FollowableInteractions<T: UpdatableInteraction?> {

    val follow: T?

}
