package com.vimeo.networking2

data class UserInteractions(

    val addPrivacyUser: Interaction? = null,

    /**
     * Information related to the block status of this user.
     */
    val block: Interaction? = null,

    /**
     * Information related to the followed status of this user.
     */
    val follow: Interaction? = null,

    /**
     * Information regarding where and how to report a user.
     */
    val report: Interaction? = null

)
