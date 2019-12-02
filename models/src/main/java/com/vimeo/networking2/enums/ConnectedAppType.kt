package com.vimeo.networking2.enums

/**
 * An enumeration of the supported connected app types.
 */
@Suppress("unused")
enum class ConnectedAppType(override val value: String?) : StringValue {

    /**
     * Represents a connection to Facebook.
     */
    FACEBOOK("facebook"),

    /**
     * Represents a connection to LinkedIn.
     */
    LINKED_IN("linkedin"),

    /**
     * Represents a connection to Twitter.
     */
    TWITTER("twitter"),

    /**
     * Represents a connection to YouTube.
     */
    YOUTUBE("youtube"),

    /**
     * Unknown connection type.
     */
    UNKNOWN(null);
}
