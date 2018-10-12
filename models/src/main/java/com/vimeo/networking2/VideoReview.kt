package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Information about the review page associated with a video.
 */
@JsonClass(generateAdapter = true)
data class VideoReview(

    /**
     * Setting to check if the review page is active for this video.
     */
    @Json(name = "active")
    val active: Boolean? = null,

    /**
     * Link to the Vimeo review page.
     */
    @Json(name = "link")
    val link: String? = null,

    /**
     * Setting to check if notes are enabled or disabled on the review page.
     *
     * Requires [CapabilitiesType.CAPABILITY_VIDEO_REVIEW].
     */
    @Json(name = "notes")
    val notes: Boolean? = null,

    /**
     * Setting to check if the vimeo logo should be displayed on the review page.
     *
     * Requires [CapabilitiesType.CAPABILITY_VIDEO_REVIEW].
     */
    @Json(name = "vimeo_logo")
    val vimeoLogo: Boolean? = null

)
