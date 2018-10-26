package com.vimeo.networking2

import com.vimeo.networking2.common.FollowableInteractions

/**
 * All actions that can be taken on a user.
 */
data class UserInteractions(

    /**
     * Disallow a user from viewing a private channel.
     */
    val addPrivacyUser: BasicInteraction? = null,

    /**
     * Information related to the block status of this user.
     */
    val block: BasicInteraction? = null,

    /**
     * Information related to the followed status of this user.
     */
    override val follow: FollowInteraction? = null,

    /**
     * Information regarding where and how to report a user.
     */
    val report: BasicInteraction? = null

): FollowableInteractions
