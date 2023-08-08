package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * User capabilities.
 */
@JsonClass(generateAdapter = true)
data class Capabilities(

    /**
     * If user has restricted privacy options enabled.
     */
    @Json(name = "restricted_privacy_options")
    val restrictedPrivacyOptions: Boolean? = null,

    /**
     * Whether the user has access to Stock Enterprise.
     */
    @Json(name = "enterprise")
    val enterprise: Boolean? = null,

    /**
     * Whether the user has access to the personal team folder.
     */
    @Json(name = "personal_team_folder")
    val personalTeamFolder: Boolean? = null,

    /**
     * Whether the user has shared with me capability.
     */
    @Json(name = "shared_with_me")
    val sharedWithMe: Boolean? = null,

    /**
     * Whether the user has enterprise lihp capability.
     */
    @Json(name = "enterprise_lihp")
    val enterpriseLihp: Boolean? = null,

    /**
     * Whether the "Hide from vimeo" privacy should be removed.
     */
    @Json(name = "sunset_hide_from_vimeo")
    val sunsetHideFromVimeo: Boolean? = null,

    /**
     * Whether the user has a Vimeo Live subscription.
     */
    @Json(name = "live_subscription")
    val liveSubscription: Boolean? = null,

    /**
     * Whether the user has a Vimeo Live subscription.
     */
    @Json(name = "contributor_plus_enabled")
    val contributorPlusEnabled: Boolean? = null,

    /**
     * Whether the user's team members can mention each other in private comments.
     */
    @Json(name = "team_mentions")
    val teamMentions: Boolean? = null
)
