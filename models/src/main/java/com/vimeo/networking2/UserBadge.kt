@file:JvmName("UserBadgeUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.UserBadgeType
import com.vimeo.networking2.enums.asEnum

/**
 * User's badge information.
 *
 * Requires [CapabilitiesType.CAPABILITY_VIEW_USER_BADGE].
 */
@JsonClass(generateAdapter = true)
data class UserBadge(

    /**
     * The badge's alternate text.
     */
    @Json(name = "alt_text")
    val altText: String? = null,

    /**
     * The text of the badge.
     */
    @Json(name = "text")
    val text: String? = null,

    /**
     * The type of the badge.
     */
    @Json(name = "type")
    val type: String? = null,

    /**
     * The URL that loads when the user clicks the badge.
     */
    @Json(name = "url")
    val url: String? = null

)

/**
 * @see UserBadge.type
 */
val UserBadge.badgeType: UserBadgeType
    get() = type.asEnum(UserBadgeType.UNKNOWN)
