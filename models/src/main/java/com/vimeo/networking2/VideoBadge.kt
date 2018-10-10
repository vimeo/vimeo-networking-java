package com.vimeo.networking2

import com.vimeo.networking2.enums.VideoBadgeType

/**
 * Video badge data.
 */
data class VideoBadge(

    /**
     * The festival that this badge represents.
     *
     * Requires [CapabilitiesType.CAPABILITY_VIEW_VIDEO_BADGE].
     */
    val festival: String? = null,

    /**
     * The link for the badge
     */
    val link: String? = null,

    /**
     * The badge image.
     */
    val pictures: PictureCollection? = null,

    /**
     * The name of the badge.
     */
    val text: String? = null,

    /**
     * The type of the badge.
     */
    val type: VideoBadgeType? = null

)
