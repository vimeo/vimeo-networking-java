package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Represents the required data for a YouTube post.
 */
@JsonClass(generateAdapter = true)
data class PublishToYouTubePost(

        /**
         * The title of the video as it will appear on YouTube.
         */
        @Json(name = "title")
        val title: String,

        /**
         * Am optional description of the video as it will appear on YouTube.
         */
        @Json(name = "description")
        val description: String? = null,

        /**
         * An optional list of tags for the video on YouTube.
         */
        @Json(name = "tags")
        val tags: List<String>? = null,

        /**
         * The privacy option for this video on YouTube.
         * public: The video will be publicly available.
         * private: The video will not be publicly available, and only viewable to the owner.
         */
        @Json(name = "privacy")
        val privacy: String,

        /**
         * The YouTube category of the video.
         */
        @Json(name = "category_id")
        val categoryId: String

)
