package com.vimeo.networking2.enums

/**
 * User account type.
 */
enum class AccountType(override val value: String?) : StringValue {

    /**
     * The user has a Vimeo Basic subscription.
     */
    BASIC("basic"),

    /**
     * The user has a Vimeo Business subscription.
     */
    BUSINESS("business"),

    /**
     * The user has a Live Business subscription.
     */
    LIVE_BUSINESS("live_business"),

    /**
     * The user has a Live Premium subscription.
     */
    LIVE_PREMIUM("live_premium"),

    /**
     * The user has a Live PRO subscription.
     */
    LIVE_PRO("live_pro"),

    /**
     * The user has a Vimeo Plus subscription.
     */
    PLUS("plus"),

    /**
     * The user has a Vimeo PRO subscription.
     */
    PRO("pro"),

    /**
     * The user has a Vimeo PRO Unlimited subscription.
     */
    PRO_UNLIMITED("pro_unlimited"),

    /**
     * The user has a Vimeo Producer subscription.
     */
    PRODUCER("producer"),

    /**
     * The user has an enterprise subscription.
     */
    ENTERPRISE("enterprise"),

    /**
     * The user has a free subscription.
     */
    FREE("free"),

    /**
     * The user has a starter subscription.
     */
    STARTER("starter"),

    /**
     * The user has a standard subscription.
     */
    STANDARD("standard"),

    /**
     * The user has an advanced subscription.
     */
    ADVANCED("advanced"),

    /**
     * The user has a custom subscription.
     */
    CUSTOM("custom"),

    /**
     * Unknown account type.
     */
    UNKNOWN(null)
}
