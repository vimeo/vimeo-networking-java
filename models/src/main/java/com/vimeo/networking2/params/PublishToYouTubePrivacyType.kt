package com.vimeo.networking2.params

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * The allowable privacy settings when publishing a video to YouTube.
 */
@JsonClass(generateAdapter = true)
enum class PublishToYouTubePrivacyType {

    /**
     * The video will be publicly available.
     */
    @Json(name = "public")
    PUBLIC,

    /**
     * The video will not be publicly available, and only viewable to the owner.
     */
    @Json(name = "private")
    PRIVATE

}
