package com.vimeo.networking2.enums

/**
 * Follow types.
 */
enum class FollowType(override val value: String?) : StringValue {

    /**
     * The authenticated user.
     */
    MODERATOR("moderator"),

    /**
     * The authenticated user is a subscriber.
     */
    SUBSCRIBER("subscriber"),

    /**
     * Unknown follow type.
     */
    UNKNOWN(null)

}
