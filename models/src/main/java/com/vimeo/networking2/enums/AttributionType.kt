package com.vimeo.networking2.enums

/**
 * Type of activity in the feed.
 */
enum class AttributionType(override val value: String?) : StringValue {

    APPEARANCE("appearance"),

    CATEGORY("category"),

    CHANNEL("channel"),

    FACEBOOK_FEED("facebook_feed"),

    GROUP("group"),

    LIKE("like"),

    ONDEMAND("ondemand"),

    SHARE("share"),

    TAG("tag"),

    TWITTER_TIMELINE("twitter_timeline"),

    UPLOAD("upload"),

    UNKNOWN(null)

}
