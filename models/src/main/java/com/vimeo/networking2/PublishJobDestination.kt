package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.Entity
import com.vimeo.networking2.enums.PublishStatusType
import com.vimeo.networking2.enums.asEnum

/**
 * Encapsulates the destination, status, and identity of a post/job on a social network.
 */
@JsonClass(generateAdapter = true)
data class PublishJobDestination(

        /**
         * The [PublishStatusType] of the connected job as a String.
         */
        @Json(name = "status")
        val status: String? = null,

        /**
         * The url of the post/job on the specified social network.
         */
        @Json(name = "third_party_post_url")
        val url: String? = null,

        /**
         * The id of the post/job on the specified social network.
         */
        @Json(name = "third_party_post_id")
        val id: String? = null

) : Entity {
    override val identifier: String? = id
}

/**
 * @see PublishJobDestination.status
 * @see PublishStatusType
 */
val PublishJobDestination.statusType: PublishStatusType
    get() = status.asEnum(PublishStatusType.UNKNOWN)
