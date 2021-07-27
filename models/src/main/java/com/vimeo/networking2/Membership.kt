@file:JvmName("MembershipUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal
import com.vimeo.networking2.enums.AccountType
import com.vimeo.networking2.enums.asEnum

/**
 * This object contains user membership information.
 *
 * @param display The user's membership level.
 * @param rawType The user's account type. See [Membership.type].
 * @param badge Information about the user's badge.
 * @param subscription Information about the user's subscription.
 */
@JsonClass(generateAdapter = true)
data class Membership(

    @Json(name = "display")
    val display: String? = null,

    @Json(name = "type")
    val rawType: String? = null,

    @Internal
    @Json(name = "badge")
    val badge: UserBadge? = null,

    @Json(name = "subscription")
    val subscription: Subscription? = null
)

/**
 * @see [Membership.rawType]
 * @see AccountType
 */
val Membership.type: AccountType
    get() = rawType.asEnum(AccountType.UNKNOWN)
