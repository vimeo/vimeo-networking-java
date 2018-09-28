package com.vimeo.networking2

/**
 * Based on CAPABILITY_VIDEO_REVIEW.
 */
data class ReviewPage(

    /**
     * Setting to check if the review page is active for this video.
     */
    val active: Boolean?,

    /**
     * Link to the Vimeo review page.
     */
    val link: String?,

    /**
     * Setting to check if notes are enabled or disabled on the review page.
     */
    val notes: String?,

    /**
     * Setting to check if the vimeo logo should be displayed on the review page.
     */
    val vimeoLogo: Boolean?

)
