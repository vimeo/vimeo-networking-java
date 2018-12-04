package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
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
    val display: String,

    /**
     * The user's account type.
     */
    @Json(name = "type")
    val type: String,

    /**
     * Information about the user's badge.
     */
    @Json(name = "badge")
    val badge: UserBadge? = null,

    /**
     * Information about the user's subscription.
     */
    @Json(name = "subscription")
    val subscription: Subscription? = null
)

/**
 * @see AccountType
 */
val Membership.accountType: AccountType
    get() = type.asEnum(AccountType.UNKNOWN)
