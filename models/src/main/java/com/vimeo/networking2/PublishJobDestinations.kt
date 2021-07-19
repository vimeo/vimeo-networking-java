package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Contains information about upload/post status on all third party social networks available for publishing.
 *
 * @param facebook Information about the upload/post on Facebook.
 * @param youTube Information about the upload/post on YouTube.
 * @param linkedIn Information about the upload/post on LinkedIn.
 * @param twitter Information about the upload/post on Twitter.
 */
@JsonClass(generateAdapter = true)
data class PublishJobDestinations(

    @Json(name = "facebook")
    val facebook: PublishJobDestination? = null,

    @Json(name = "youtube")
    val youTube: PublishJobDestination? = null,

    @Json(name = "linkedin")
    val linkedIn: PublishJobDestination? = null,

    @Json(name = "twitter")
    val twitter: PublishJobDestination? = null
)
