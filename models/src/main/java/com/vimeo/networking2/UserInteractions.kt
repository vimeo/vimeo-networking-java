package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.FollowableInteractions
import java.io.Serializable

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
    val report: BasicInteraction? = null,

    /**
     * Information related to the Facebook connected app.
     */
    @Json(name = "facebook_connected_app")
    val facebookConnectedApp: ConnectedAppInteraction? = null,

    /**
     * Information related to the YouTube connected app.
     */
    @Json(name = "youtube_connected_app")
    val youTubeConnectedApp: ConnectedAppInteraction? = null,

    /**
     * Information related to the LinkedIn connected app.
     */
    @Json(name = "linkedin_connected_app")
    val linkedInConnectedApp: ConnectedAppInteraction? = null,

    /**
     * Information related to the Twitter connected app.
     */
    @Json(name = "twitter_connected_app")
    val twitterConnectedApp: ConnectedAppInteraction? = null

) : FollowableInteractions, Serializable {

    companion object {
        private const val serialVersionUID = -18L
    }
}
