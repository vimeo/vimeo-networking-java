package com.vimeo.networking2

data class UserInteractions(

    val addPrivacyUser: Interaction?,

    /**
     * Information related to the block status of this user.
     */
    val block: Interaction?,

    /**
     * Information related to the followed status of this user.
     */
    val follow: Interaction?,

    /**
     * Information regarding where and how to report a user.
     */
    val report: Interaction?

)
