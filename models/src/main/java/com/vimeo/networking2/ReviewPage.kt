package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal

/**
 * Review page information.
 *
 * @param active Setting to check if the review page is active for this video.
 * @param link Link to the Vimeo review page.
 * @param notes Setting to check if notes are enabled or disabled on the review page.
 * @param vimeoLogo Setting to check if the vimeo logo should be displayed on the review page.
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
    val notes: String? = null,

    @Internal
    @Json(name = "vimeo_logo")
    val vimeoLogo: Boolean? = null

)
