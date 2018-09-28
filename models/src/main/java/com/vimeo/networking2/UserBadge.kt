package com.vimeo.networking2

/**
 * Based on CAPABILITY_VIEW_USER_BADGE.
 */
data class UserBadge(

    /**
     * The badge's alternate text.
     */
    val altText: String?,

    /**
     * The text of the badge.
     */
    val text: String?,

    /**
     * The type of the badge.
     */
    val type: UseBadgeType?,

    /**
     * The URL that loads when the user clicks the badge.
     */
    val url: String?

)
