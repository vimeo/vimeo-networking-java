package com.vimeo.networking2.enums

import com.squareup.moshi.Json

enum class AccountType {

    /**
     * The user has a Vimeo Basic subscription.
     */
    @Json(name = "basic")
    BASIC,

    /**
     * The user has a Vimeo Business subscription.
     */
    @Json(name = "business")
    BUSINESS,

    /**
     * The user has a Live Business subscription.
     */
    @Json(name = "live business")
    LIVE_BUSINESS,

    /**
     * The user has a Live Premium subscription.
     */
    @Json(name = "live premium")
    LIVE_PREMIUM,

    /**
     * The user has a Live PRO subscription
     */
    @Json(name = "live pro")
    LIVE_PRO,

    /**
     * The user has a Vimeo Plus subscription.
     */
    @Json(name = "plus")
    PLUS,

    /**
     * The user has a Vimeo PRO subscription.
     */
    @Json(name = "pro")
    PRO,

    /**
     * The user has a Vimeo PRO Unlimited subscription.
     */
    @Json(name = "pro unlimited")
    PRO_UNLIMITED,

    /**
     * The user has a Vimeo Producer subscription.
     */
    @Json(name = "producer")
    PRODUCER

}
