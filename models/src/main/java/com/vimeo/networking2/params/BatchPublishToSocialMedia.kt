package com.vimeo.networking2.params

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 *  Encapsulates publishing data for each of the supported social media platforms.
 */
data class BatchPublishToSocialMedia(

        /**
         * Optional publishing data for Facebook.
         */
        @SerializedName(value = "facebook")
        val facebook: PublishToFacebookPost? = null,

        /**
         * Optional publishing data for YouTube.
         */
        @SerializedName(value = "youtube")
        val youTube: PublishToYouTubePost? = null,

        /**
         * Optional publishing data for Twitter.
         */
        @SerializedName(value = "twitter")
        val twitter: PublishToTwitterPost? = null,

        /**
         * Optional publishing data for LinkedIn.
         */
        @SerializedName(value = "linkedin")
        val linkedIn: PublishToLinkedInPost? = null
)
