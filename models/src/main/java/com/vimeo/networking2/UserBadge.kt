package com.vimeo.networking2

import com.vimeo.networking2.enums.UserBadgeType

/**
 * User's badge information.
 *
 * Requires [CapabilitiesType.CAPABILITY_VIEW_USER_BADGE].
 */
data class UserBadge(

    /**
     * The badge's alternate text.
     */
    val altText: String? = null,

    /**
     * The text of the badge.
     */
    val text: String? = null,

    /**
     * The type of the badge.
     */
    val type: UserBadgeType = UserBadgeType.UNKNOWN,

    /**
     * The URL that loads when the user clicks the badge.
     */
    val url: String? = null

)
