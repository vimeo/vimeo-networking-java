package com.vimeo.networking2

/**
 * User's upload quota information.
 */
data class UploadQuota(

    /**
     * The number of bytes remaining in your lifetime maximum.
     */
    val lifetime: Lifetime? = null,

    /**
     * The number of bytes remaining in your upload quota for the current period.
     */
    val periodic: Periodic? = null,

    /**
     * Quota information.
     */
    val quota: Quota? = null,

    /**
     * Space information.
     */
    val space: Space? = null

)
