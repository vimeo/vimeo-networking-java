package com.vimeo.networking2.enums

/**
 * Different types of user badges.
 */
enum class UserBadgeType(override val value: String?) : StringValue {

    /**
     * The user is a Vimeo alumni.
     */
    ALUM("alum"),

    /**
     * The user has a Vimeo Business subscription.
     */
    BUSINESS("business"),

    /**
     * The user is part of the Vimeo curation team.
     */
    CURATION("curation"),

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
     * The user is or was the President of the United States.
     */
    POTUS("potus"),

    /**
     * The user has a Vimeo PRO subscription.
     */
    PRO("pro"),

    /**
     * The user has a Vimeo Producer subscription.
     */
    PRODUCER("producer"),

    /**
     * The user is a sponsor.
     */
    SPONSOR("sponsor"),

    /**
     * The user is a current Vimeo staff member.
     */
    STAFF("staff"),

    /**
     * The user is on the Vimeo support team.
     */
    SUPPORT("support"),

    /**
     * Unknown type of badge.
     */
    UNKNOWN(null)

}
