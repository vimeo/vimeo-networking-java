@file:JvmName("FollowableUtils")

package com.vimeo.networking2.extensions

import com.vimeo.networking2.common.Followable

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
