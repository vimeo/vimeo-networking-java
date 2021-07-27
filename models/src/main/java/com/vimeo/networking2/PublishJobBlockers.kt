@file:JvmName("PublishJobBlockersUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.BlockerType
import com.vimeo.networking2.enums.asEnumList

/**
 * An object that represents the blockers keeping a video from being published to each platform.
 *
 * @param facebook The list of blockers keeping this video from being uploaded to Facebook. See
 * [PublishJobBlockers.facebookTypes].
 * @param youtube The list of blockers keeping this video from being uploaded to YouTube. See
 * [PublishJobBlockers.youTubeTypes].
 * @param linkedin The list of blockers keeping this video from being uploaded to LinkedIn. See
 * [PublishJobBlockers.linkedinTypes].
 * @param twitter The list of blockers keeping this video from being uploaded to Twitter. See
 * [PublishJobBlockers.twitterTypes].
 */
@JsonClass(generateAdapter = true)
data class PublishJobBlockers(

    @Json(name = "facebook")
    val facebook: List<String>? = null,

    @Json(name = "youtube")
    val youtube: List<String>? = null,

    @Json(name = "linkedin")
    val linkedin: List<String>? = null,

    @Json(name = "twitter")
    val twitter: List<String>? = null
)

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
