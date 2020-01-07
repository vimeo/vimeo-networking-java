package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.BlockerType
import com.vimeo.networking2.enums.asEnumList

/**
 * Created by nicholas.doglio on 2020-01-07.
 */
@JsonClass(generateAdapter = true)
data class PublishBlockers(
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
 * @see PublishBlockers.facebook
 * @see BlockerType
 */
val PublishBlockers.facebookTypes: List<BlockerType>
    get() = facebook.asEnumList(BlockerType.UNKNOWN)

/**
 * @see PublishBlockers.youtube
 * @see BlockerType
 */
val PublishBlockers.youTubeTypes: List<BlockerType>
    get() = youtube.asEnumList(BlockerType.UNKNOWN)

/**
 * @see PublishBlockers.linkedin
 * @see BlockerType
 */
val PublishBlockers.linkedinTypes: List<BlockerType>
    get() = linkedin.asEnumList(BlockerType.UNKNOWN)

/**
 * @see PublishBlockers.twitter
 * @see BlockerType
 */
val PublishBlockers.twitterTypes: List<BlockerType>
    get() = twitter.asEnumList(BlockerType.UNKNOWN)
