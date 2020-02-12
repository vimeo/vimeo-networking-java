package com.vimeo.networking2.params

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Represents the required data for a Facebook post.
 */
@JsonClass(generateAdapter = true)
data class PublishToFacebookPost(

        /**
         * The title of the post as it will appear on Facebook.
         */
        @Json(name = "title")
        val title: String?,

        /**
         * The description of the post as it will appear on Facebook.
         */
        @Json(name = "description")
        val description: String?,

        /**
         * The destination identifier (page id) of the page being posted to on Facebook.
         */
        @Json(name = "destination")
        val destination: Long,

        /**
         * An optional Facebook category of the video.
         */
        @Json(name = "category_id")
        val categoryId: String? = null,

        /**
         * Whether or not this Facebook post should be embeddable.
         */
        @Json(name = "allow_embedding")
        val allowEmbedding: Boolean,

        /**
         * Whether or not this post should appear on the Facebook News Feed.
         */
        @Json(name = "should_appear_on_news_feed")
        val shouldAppearOnNewsFeed: Boolean,

        /**
         * Whether or not this video should be searchable and show up
         * in the user's video library on Facebook.
         */
        @Json(name = "is_secret_video")
        val isSecretVideo: Boolean,

        /**
         * Whether or not to allow social actions on the post on Facebook.
         */
        @Json(name = "allow_social_actions")
        val allowSocialActions: Boolean

)
