package com.vimeo.networking2.enums

import com.squareup.moshi.Json

/**
 * Type of activity in the feed.
 */
enum class AttributionType {

    @Json(name = "appearance")
    APPEARANCE,

    @Json(name = "category")
    CATEGORY,

    @Json(name = "channel")
    CHANNEL,

    @Json(name = "facebook_feed")
    FACEBOOK_FEED,

    @Json(name = "group")
    GROUP,

    @Json(name = "like")
    LIKE,

    @Json(name = "ondemand")
    ONDEMAND,

    @Json(name = "share")
    SHARE,

    @Json(name = "tag")
    TAG,

    @Json(name = "twitter_timeline")
    TWITTER_TIMELINE,

    @Json(name = "upload")
    UPLOAD

}
