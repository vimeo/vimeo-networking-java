package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Contains information about upload/post status on all
 * third party social networks available for publishing.
 */
@JsonClass(generateAdapter = true)
data class PublishJobDestinations(

    /**
     * Information about the upload/post on Facebook.
     */
    @Json(name = "facebook")
    val facebook: PublishJobDestination? = null,

    /**
     * Information about the upload/post on YouTube.
     */
    @Json(name = "youtube")
    val youTube: PublishJobDestination? = null,

    /**
     * Information about the upload/post on LinkedIn.
     */
    @Json(name = "linkedin")
    val linkedIn: PublishJobDestination? = null,

    /**
     * Information about the upload/post on Twitter.
     */
    @Json(name = "twitter")
    val twitter: PublishJobDestination? = null

)
