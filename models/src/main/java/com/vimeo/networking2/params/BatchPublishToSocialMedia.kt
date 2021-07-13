package com.vimeo.networking2.params

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Encapsulates publishing data for each of the supported social media platforms.
 *
 * @param facebook Optional publishing data for Facebook.
 * @param youTube Optional publishing data for YouTube.
 * @param twitter Optional publishing data for Twitter.
 * @param linkedIn Optional publishing data for LinkedIn.
 */
@JsonClass(generateAdapter = true)
data class BatchPublishToSocialMedia(

    @Json(name = "facebook")
    val facebook: PublishToFacebookPost? = null,

    @Json(name = "youtube")
    val youTube: PublishToYouTubePost? = null,

    @Json(name = "twitter")
    val twitter: PublishToTwitterPost? = null,

    @Json(name = "linkedin")
    val linkedIn: PublishToLinkedInPost? = null
)
