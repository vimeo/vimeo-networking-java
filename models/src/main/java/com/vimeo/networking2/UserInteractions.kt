package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.FollowableInteractions

/**
 * All actions that can be taken on a user.
 *
 * @param addPrivacyUser Disallow a user from viewing a private channel.
 * @param block Information related to the block status of this user.
 * @param report Information regarding where and how to report a user.
 * @param facebookConnectedApp Information related to the Facebook connected app.
 * @param youTubeConnectedApp Information related to the YouTube connected app.
 * @param linkedInConnectedApp Information related to the LinkedIn connected app.
 * @param twitterConnectedApp Information related to the Twitter connected app.
 */
@JsonClass(generateAdapter = true)
data class UserInteractions(

    @Json(name = "add_privacy_user")
    val addPrivacyUser: BasicInteraction? = null,

    @Json(name = "block")
    val block: BasicInteraction? = null,

    @Json(name = "follow")
    override val follow: FollowInteraction? = null,

    @Json(name = "report")
    val report: BasicInteraction? = null,

    @Json(name = "facebook_connected_app")
    val facebookConnectedApp: ConnectedAppInteraction? = null,

    @Json(name = "youtube_connected_app")
    val youTubeConnectedApp: ConnectedAppInteraction? = null,

    @Json(name = "linkedin_connected_app")
    val linkedInConnectedApp: ConnectedAppInteraction? = null,

    @Json(name = "twitter_connected_app")
    val twitterConnectedApp: ConnectedAppInteraction? = null

) : FollowableInteractions
