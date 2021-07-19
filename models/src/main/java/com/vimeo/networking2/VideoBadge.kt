@file:JvmName("VideoBadgeUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal
import com.vimeo.networking2.enums.VideoBadgeType
import com.vimeo.networking2.enums.asEnum

/**
 * Video badge data.
 *
 * @param festival The festival that this badge represents.
 * @param link The link for the badge.
 * @param pictures The badge image.
 * @param text The name of the badge.
 * @param rawType The type of the badge. See [VideoBadge.type].
 */
@JsonClass(generateAdapter = true)
data class VideoBadge(

    @Internal
    @Json(name = "festival")
    val festival: String? = null,

    @Json(name = "link")
    val link: String? = null,

    @Json(name = "pictures")
    val pictures: PictureCollection? = null,

    @Json(name = "text")
    val text: String? = null,

    @Json(name = "type")
    val rawType: String? = null

)

/**
 * @see VideoBadge.rawType
 * @see VideoBadgeType
 */
val VideoBadge.type: VideoBadgeType
    get() = rawType.asEnum(VideoBadgeType.UNKNOWN)
