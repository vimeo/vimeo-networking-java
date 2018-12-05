package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal
import com.vimeo.networking2.enums.AccountType
import com.vimeo.networking2.enums.asEnum

/**
 * This object contains user membership information.
 */
@JsonClass(generateAdapter = true)
data class Membership(

    /**
     * The user's membership level
     */
    @Json(name = "display")
    val display: String? = null,

    /**
     * The user's account type.
     * @see [Membership.type]
     */
    @Json(name = "type")
    val rawType: String? = null,

    /**
     * Information about the user's badge.
     */
    @Internal
    @Json(name = "badge")
    val badge: UserBadge? = null,

    /**
     * Information about the user's subscription.
     */
    @Json(name = "subscription")
    val subscription: Subscription? = null
)

/**
 * @see [Membership.rawType]
 * @see AccountType
 */
val Membership.type: AccountType
    get() = rawType.asEnum(AccountType.UNKNOWN)
