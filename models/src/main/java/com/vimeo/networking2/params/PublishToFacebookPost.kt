package com.vimeo.networking2.params

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Represents the required data for a Facebook post.
 */
data class PublishToFacebookPost(

        /**
         * The title of the post as it will appear on Facebook.
         */
        @SerializedName(value = "title")
        val title: String,

        /**
         * The description of the post as it will appear on Facebook.
         */
        @SerializedName(value = "description")
        val description: String,

        /**
         * The destination identifier (page id) of the page being posted to on Facebook.
         */
        @SerializedName(value = "destination")
        val destination: Long,

        /**
         * An optional Facebook category of the video.
         */
        @SerializedName(value = "category_id")
        val categoryId: String? = null,

        /**
         * Whether or not this Facebook post should be embeddable.
         */
        @SerializedName(value = "allow_embedding")
        val allowEmbedding: Boolean,

        /**
         * Whether or not this post should appear on the Facebook News Feed.
         */
        @SerializedName(value = "should_appear_on_news_feed")
        val shouldAppearOnNewsFeed: Boolean,

        /**
         * Whether or not this video should be searchable and show up
         * in the user's video library on Facebook.
         */
        @SerializedName(value = "is_secret_video")
        val isSecretVideo: Boolean,

        /**
         * Whether or not to allow social actions on the post on Facebook.
         */
        @SerializedName(value = "allow_social_actions")
        val allowSocialActions: Boolean

)
