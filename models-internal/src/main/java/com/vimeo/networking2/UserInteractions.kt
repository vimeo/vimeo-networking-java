package com.vimeo.networking2

import com.vimeo.networking2.common.FollowableInteractions
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * All actions that can be taken on a user.
 */
@JsonClass(generateAdapter = true)
data class UserInteractions(

    /**
     * Disallow a user from viewing a private channel.
     */
    @Json(name = "add_privacy_user")
    val addPrivacyUser: BasicInteraction? = null,

    /**
     * Information related to the block status of this user.
     */
    @Json(name = "block")
    val block: BasicInteraction? = null,

    /**
     * Information related to the followed status of this user.
     */
    @Json(name = "follow")
    override val follow: FollowInteraction? = null,

    /**
     * Information regarding where and how to report a user.
     */
    @Json(name = "report")
    val report: BasicInteraction? = null

): FollowableInteractions
