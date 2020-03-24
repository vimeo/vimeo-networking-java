@file:JvmName("UserBadgeUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal
import com.vimeo.networking2.enums.UserBadgeType
import com.vimeo.networking2.enums.asEnum
import java.io.Serializable

/**
 * User's badge information.
 */
@Internal
@JsonClass(generateAdapter = true)
data class UserBadge(

        /**
         * The badge's alternate text.
         */
        @Internal
        @Json(name = "alt_text")
        val altText: String? = null,

        /**
         * The text of the badge.
         */
        @Internal
        @Json(name = "text")
        val text: String? = null,

        /**
         * The type of the badge.
         * @see UserBadge.type
         */
        @Internal
        @Json(name = "type")
        val rawType: String? = null,

        /**
         * The URL that loads when the user clicks the badge.
         */
        @Internal
        @Json(name = "url")
        val url: String? = null

) : Serializable {

    companion object {
        private const val serialVersionUID = -12L
    }
}

/**
 * @see UserBadge.rawType
 */
val UserBadge.type: UserBadgeType
    get() = rawType.asEnum(UserBadgeType.UNKNOWN)
