package com.vimeo.networking2.params

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

/**
 *  Encapsulates publishing data for each of the supported social media platforms.
 */
@JsonClass(generateAdapter = true)
data class BatchPublishToSocialMedia(

        /**
         * Optional publishing data for Facebook.
         */
        @Json(name = "facebook")
        val facebook: PublishToFacebookPost? = null,

        /**
         * Optional publishing data for YouTube.
         */
        @Json(name = "youtube")
        val youTube: PublishToYouTubePost? = null,

        /**
         * Optional publishing data for Twitter.
         */
        @Json(name = "twitter")
        val twitter: PublishToTwitterPost? = null,

        /**
         * Optional publishing data for LinkedIn.
         */
        @Json(name = "linkedin")
        val linkedIn: PublishToLinkedInPost? = null
) : Serializable {

    companion object {
        private const val serialVersionUID = -5L
    }
}
