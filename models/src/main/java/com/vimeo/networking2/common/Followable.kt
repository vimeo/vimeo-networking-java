package com.vimeo.networking2.common

import com.vimeo.networking2.Metadata

/**
 * All DTOs that can be followed should conform this interface.
 */
interface Followable {

    val metadata: Metadata<*, out FollowableInteractions<*>>?

}

/**
 * Is the user following the object?
 *
 * @return true if it is, otherwise false.
 */
fun Followable.isFollowing(): Boolean = metadata?.interactions?.follow?.added == true

/**
 * Can the user follow the object?
 *
 * @return true if it is, otherwise false.
 */
fun Followable.canFollow(): Boolean = metadata?.interactions?.follow?.uri != null
