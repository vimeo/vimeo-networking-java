package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 *  Encapsulates publishing data for each of the supported social media platforms.
 */
@JsonClass(generateAdapter = true)
data class BatchPublishToSocialMedia(

        /**
         * Optional publishing data for Facebook.
         */
        @Json(name = "facebook")
        val facebookPost: PublishToFacebookPost? = null,

        /**
         * Optional publishing data for YouTube.
         */
        @Json(name = "youtube")
        val youTubePost: PublishToYouTubePost? = null,

        /**
         * Optional publishing data for Twitter.
         */
        @Json(name = "twitter")
        val twitterPost: PublishToTwitterPost? = null,

        /**
         * Optional publishing data for LinkedIn.
         */
        @Json(name = "linkedin")
        val linkedInPost: PublishToLinkedInPost? = null
)
