package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.VideoBadgeType

/**
 * Video badge data.
 */
@JsonClass(generateAdapter = true)
data class VideoBadge(

    /**
     * The festival that this badge represents.
     *
     * Requires [CapabilitiesType.CAPABILITY_VIEW_VIDEO_BADGE].
     */
    @Json(name = "festival")
    val festival: String? = null,

    /**
     * The link for the badge
     */
    @Json(name = "link")
    val link: String? = null,

    /**
     * The badge image.
     */
    @Json(name = "pictures")
    val pictures: PictureCollection? = null,

    /**
     * The name of the badge.
     */
    @Json(name = "text")
    val text: String? = null,

    /**
     * The type of the badge.
     */
    @Json(name = "type")
    val type: VideoBadgeType? = null

)
