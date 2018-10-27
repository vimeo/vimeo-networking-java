package com.vimeo.networking2.enums

import com.squareup.moshi.Json

/**
 * Different types of user badges.
 */
enum class UserBadgeType {

    /**
     * The user is a Vimeo alumni.
     */
    @Json(name = "alum")
    ALUM,

    /**
     * The user has a Vimeo Business subscription.
     */
    @Json(name = "business")
    BUSINESS,

    /**
     * The user is part of the Vimeo curation team.
     */
    @Json(name = "curation")
    CURATION,

    /**
     * The user has a Live Business subscription.
     */
    @Json(name = "live_business")
    LIVE_BUSINESS,

    /**
     * The user has a Live Premium subscription.
     */
    @Json(name = "live_premium")
    LIVE_PREMIUM,

    /**
     * The user has a Live PRO subscription.
     */
    @Json(name = "live_pro")
    LIVE_PRO,

    /**
     * The user has a Vimeo Plus subscription.
     */
    @Json(name = "plus")
    PLUS,

    /**
     * The user is or was the President of the United States.
     */
    @Json(name = "potus")
    POTUS,

    /**
     * The user has a Vimeo PRO subscription.
     */
    @Json(name = "pro")
    PRO,

    /**
     * The user has a Vimeo Producer subscription.
     */
    @Json(name = "producer")
    PRODUCER,

    /**
     * The user is a sponsor.
     */
    @Json(name = "sponsor")
    SPONSOR,

    /**
     * The user is a current Vimeo staff member.
     */
    @Json(name = "staff")
    STAFF,

    /**
     * The user is on the Vimeo support team.
     */
    @Json(name = "support")
    SUPPORT,

    /**
     * Unknown type of badge.
     */
    UNKNOWN

}
