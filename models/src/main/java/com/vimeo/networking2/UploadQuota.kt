package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * User's upload quota information.
 */
@JsonClass(generateAdapter = true)
data class UploadQuota(

    /**
     * The number of bytes remaining in your lifetime maximum.
     */
    @Json(name = "lifetime")
    val lifetime: Lifetime? = null,

    /**
     * The number of bytes remaining in your upload quota for the current period.
     */
    @Json(name = "periodic")
    val periodic: Periodic? = null,

    /**
     * Quota information.
     */
    @Json(name = "quota")
    val quota: Quota? = null,

    /**
     * Space information.
     */
    @Json(name = "space")
    val space: Space? = null

)
