@file:JvmName("PublishJobBlockersUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.BlockerType
import com.vimeo.networking2.enums.asEnumList
import java.io.Serializable

/**
 * An object that represents the blockers keeping a video from being published to each platform.
 */
@JsonClass(generateAdapter = true)
data class PublishJobBlockers(

        /**
         * The list of blockers keeping this video from being uploaded to Facebook.
         * @see PublishJobBlockers.facebookTypes
         */
        @Json(name = "facebook")
        val facebook: List<String>? = null,

        /**
         *  The list of blockers keeping this video from being uploaded to YouTube.
         *  @see PublishJobBlockers.youTubeTypes
         */

        @Json(name = "youtube")
        val youtube: List<String>? = null,

        /**
         * The list of blockers keeping this video from being uploaded to LinkedIn.
         * @see PublishJobBlockers.linkedinTypes
         */
        @Json(name = "linkedin")
        val linkedin: List<String>? = null,

        /**
         * The list of blockers keeping this video from being uploaded to Twitter.
         * @see PublishJobBlockers.twitterTypes
         */
        @Json(name = "twitter")
        val twitter: List<String>? = null
) : Serializable {

    companion object {
        private const val serialVersionUID = -36L
    }
}

/**
 * @see PublishJobBlockers.facebook
 * @see BlockerType
 */
val PublishJobBlockers.facebookTypes: List<BlockerType>
    get() = facebook.asEnumList(BlockerType.UNKNOWN)

/**
 * @see PublishJobBlockers.youtube
 * @see BlockerType
 */
val PublishJobBlockers.youTubeTypes: List<BlockerType>
    get() = youtube.asEnumList(BlockerType.UNKNOWN)

/**
 * @see PublishJobBlockers.linkedin
 * @see BlockerType
 */
val PublishJobBlockers.linkedinTypes: List<BlockerType>
    get() = linkedin.asEnumList(BlockerType.UNKNOWN)

/**
 * @see PublishJobBlockers.twitter
 * @see BlockerType
 */
val PublishJobBlockers.twitterTypes: List<BlockerType>
    get() = twitter.asEnumList(BlockerType.UNKNOWN)
