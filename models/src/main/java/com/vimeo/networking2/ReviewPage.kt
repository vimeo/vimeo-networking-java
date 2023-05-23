package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal

/**
 * Review page information.
 *
 * @param active Whether or not the review page is active for this video.
 * @param link Link to the Vimeo review page.
 * @param notes Whether or not notes are enabled on the review page.
 * @param vimeoLogo Whether or not the vimeo logo should be displayed on the review page.
 */
@Internal
@JsonClass(generateAdapter = true)
data class ReviewPage(

    @Internal
    @Json(name = "active")
    val active: Boolean? = null,

    @Internal
    @Json(name = "link")
    val link: String? = null,

    @Internal
    @Json(name = "notes")
    val notes: Boolean? = null,

    @Internal
    @Json(name = "vimeo_logo")
    val vimeoLogo: Boolean? = null

)
