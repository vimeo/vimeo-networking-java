package com.vimeo.networking2

/**
 * All DTOs that can be followed should conform this interface.
 */
interface Followable {

    val metadata: Metadata<*, out FollowableInteractions<*>>?

}
