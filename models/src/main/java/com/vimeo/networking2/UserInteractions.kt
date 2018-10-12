package com.vimeo.networking2

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
    val addPrivacyUser: Interaction? = null,

    /**
     * Information related to the block status of this user.
     */
    @Json(name = "block")
    val block: Interaction? = null,

    /**
     * Information related to the followed status of this user.
     */
    @Json(name = "follow")
    val follow: Interaction? = null,

    /**
     * Information regarding where and how to report a user.
     */
    @Json(name = "report")
    val report: Interaction? = null

)
