package com.vimeo.networking2.params

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Represents the required data for a Facebook post.
 *
 * @param title The title of the post as it will appear on Facebook.
 * @param description The description of the post as it will appear on Facebook.
 * @param destination The destination identifier (page id) of the page being posted to on Facebook.
 * @param categoryId An optional Facebook category of the video.
 * @param allowEmbedding Whether or not this Facebook post should be embeddable.
 * @param shouldAppearOnNewsFeed Whether or not this post should appear on the Facebook News Feed.
 * @param isSecretVideo Whether or not this video should be searchable and show up in the user's video library on
 * Facebook.
 * @param allowSocialActions Whether or not to allow social actions on the post on Facebook.
 */
@JsonClass(generateAdapter = true)
data class PublishToFacebookPost(

    @Json(name = "title")
    val title: String? = null,

    @Json(name = "description")
    val description: String? = null,

    @Json(name = "destination")
    val destination: Long,

    @Json(name = "category_id")
    val categoryId: String? = null,

    @Json(name = "allow_embedding")
    val allowEmbedding: Boolean,

    @Json(name = "should_appear_on_news_feed")
    val shouldAppearOnNewsFeed: Boolean,

    @Json(name = "is_secret_video")
    val isSecretVideo: Boolean,

    @Json(name = "allow_social_actions")
    val allowSocialActions: Boolean
)
