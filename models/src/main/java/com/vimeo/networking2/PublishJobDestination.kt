@file:JvmName("PublishJobDestinationUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.Entity
import com.vimeo.networking2.enums.PublishStatusType
import com.vimeo.networking2.enums.asEnum

/**
 * Encapsulates the destination, status, and identity of a post/job on a social network.
 *
 * @param status The [PublishStatusType] of the connected job as a String.
 * @param url The url of the post/job on the specified social network.
 * @param id The id of the post/job on the specified social network.
 * @param viewCount The number of views of the post/job on the specified social network.
 * @param likeCount The number of likes of the post/job on the specified social network.
 * @param commentCount The number of comments of the post/job on the specified social network.
 */
@JsonClass(generateAdapter = true)
data class PublishJobDestination(

    @Json(name = "status")
    val status: String? = null,

    @Json(name = "third_party_post_url")
    val url: String? = null,

    @Json(name = "third_party_post_id")
    val id: String? = null,

    @Json(name = "third_party_view_count")
    val viewCount: Long? = null,

    @Json(name = "third_party_like_count")
    val likeCount: Long? = null,

    @Json(name = "third_party_comment_count")
    val commentCount: Long? = null
) : Entity {
    override val identifier: String? = id
}

/**
 * @see PublishJobDestination.status
 * @see PublishStatusType
 */
val PublishJobDestination.statusType: PublishStatusType
    get() = status.asEnum(PublishStatusType.UNKNOWN)
