package com.vimeo.networking2

/**
 * Information about the review page associated with a video.
 */
data class VideoReview(

    /**
     * Setting to check if the review page is active for this video.
     */
    val active: Boolean? = null,

    /**
     * Link to the Vimeo review page.
     */
    val link: String? = null,

    /**
     * Setting to check if notes are enabled or disabled on the review page.
     *
     * Requires [CapabilitiesType.CAPABILITY_VIDEO_REVIEW].
     */
    val notes: Boolean? = null,

    /**
     * Setting to check if the vimeo logo should be displayed on the review page.
     *
     * Requires [CapabilitiesType.CAPABILITY_VIDEO_REVIEW].
     */
    val vimeoLogo: Boolean? = null

)
