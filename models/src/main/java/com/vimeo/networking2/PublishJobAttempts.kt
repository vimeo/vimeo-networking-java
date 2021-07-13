package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * An object representing information on previous attempts to publish to
 * third-party social networks.
 *
 * @param facebook true or false depending on whether a previous attempt was made to publish the video to Facebook. Note
 * that if a previous attempt failed, this value will still be true.
 * @param youtube true or false depending on whether a previous attempt was made to publish the video to YouTube. Note
 * that if a previous attempt failed, this value will still be true.
 * @param linkedin true or false depending on whether a previous attempt was made to publish the video to LinkedIn. Note
 * that if a previous attempt failed, this value will still be true.
 * @param twitter true or false depending on whether a previous attempt was made to publish the video to Twitter. Note
 * that if a previous attempt failed, this value will still be true.
 */
@JsonClass(generateAdapter = true)
data class PublishJobAttempts(

    @Json(name = "facebook")
    val facebook: Boolean? = null,

    @Json(name = "youtube")
    val youtube: Boolean? = null,

    @Json(name = "linkedin")
    val linkedin: Boolean? = null,

    @Json(name = "twitter")
    val twitter: Boolean? = null

)
