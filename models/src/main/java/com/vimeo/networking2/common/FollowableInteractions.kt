package com.vimeo.networking2.common

import com.vimeo.networking2.UpdatableInteraction

/**
 * Followable interactions.
 */
interface FollowableInteractions<T: UpdatableInteraction?> {

    val follow: T?

}
