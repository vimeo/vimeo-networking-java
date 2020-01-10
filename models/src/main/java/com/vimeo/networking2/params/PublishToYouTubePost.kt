package com.vimeo.networking2.params

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Represents the required data for a YouTube post.
 */
data class PublishToYouTubePost(

        /**
         * The title of the video as it will appear on YouTube.
         */
        @SerializedName(value = "title")
        val title: String,

        /**
         * Am optional description of the video as it will appear on YouTube.
         */
        @SerializedName(value = "description")
        val description: String? = null,

        /**
         * An optional list of tags for the video on YouTube.
         */
        @SerializedName(value = "tags")
        val tags: List<String>? = null,

        /**
         * The privacy option for this video on YouTube.
         */
        @SerializedName(value = "privacy")
        val privacy: PublishToYouTubePrivacyType,

        /**
         * The YouTube category of the video.
         */
        @SerializedName(value = "category_id")
        val categoryId: String

)
