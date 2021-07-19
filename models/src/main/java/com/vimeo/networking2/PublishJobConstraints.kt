package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * An object representing the information on the publishing constraints for each social network.
 *
 * @param facebook The publish constraints for Facebook.
 * @param linkedin The publish constraints for LinkedIn.
 * @param youtube The publish constraints for YouTube.
 * @param twitter The publish constraints for Twitter.
 */
@JsonClass(generateAdapter = true)
data class PublishJobConstraints(

    @Json(name = "facebook")
    val facebook: PlatformConstraint? = null,

    @Json(name = "linkedin")
    val linkedin: PlatformConstraint? = null,

    @Json(name = "youtube")
    val youtube: PlatformConstraint? = null,

    @Json(name = "twitter")
    val twitter: PlatformConstraint? = null
)
