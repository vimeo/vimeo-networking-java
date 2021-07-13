@file:JvmName("UserBadgeUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal
import com.vimeo.networking2.enums.UserBadgeType
import com.vimeo.networking2.enums.asEnum

/**
 * User's badge information.
 *
 * @param altText The badge's alternate text.
 * @param text The text of the badge.
 * @param rawType The type of the badge. See [UserBadge.type].
 * @param url The URL that loads when the user clicks the badge.
 */
@Internal
@JsonClass(generateAdapter = true)
data class UserBadge(

    @Internal
    @Json(name = "alt_text")
    val altText: String? = null,

    @Internal
    @Json(name = "text")
    val text: String? = null,

    @Internal
    @Json(name = "type")
    val rawType: String? = null,

    @Internal
    @Json(name = "url")
    val url: String? = null

)

/**
 * @see UserBadge.rawType
 */
val UserBadge.type: UserBadgeType
    get() = rawType.asEnum(UserBadgeType.UNKNOWN)
