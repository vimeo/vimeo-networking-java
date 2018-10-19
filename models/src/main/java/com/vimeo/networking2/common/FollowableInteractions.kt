package com.vimeo.networking2.common

/**
 * Followable interactions.
 */
interface FollowableInteractions<T: UpdatableInteraction?> {

    val follow: T?

}
