package com.vimeo.networking2.enums

import com.squareup.moshi.Json

/**
 * Follow types.
 */
enum class FollowType {

    /**
     * The authenticated user.
     */
    @Json(name = "moderator")
    MODERATOR,

    /**
     * The authenticated user is a subscriber.
     */
    @Json(name = "subscriber")
    SUBSCRIBER,

    /**
     * Unknown follow type.
     */
    UNKNOWN

}
