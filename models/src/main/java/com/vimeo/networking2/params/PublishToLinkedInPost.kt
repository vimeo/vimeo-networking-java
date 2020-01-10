package com.vimeo.networking2.params

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Represents the required data for a LinkedIn post.
 */
data class PublishToLinkedInPost(

        /**
         * The LinkedIn page identifier that the video will be posted to.
         */
        @SerializedName(value = "page_id")
        val pageID: Int,

        /**
         * The title of the post as it will appear on LinkedIn.
         */
        @SerializedName(value = "title")
        val title: String,

        /**
         * The description of the post as it will appear on LinkedIn.
         */
        @SerializedName(value = "description")
        val description: String

)
