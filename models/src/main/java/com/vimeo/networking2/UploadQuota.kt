package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * User's upload quota information.
 *
 * @param lifetime The number of bytes remaining in your lifetime maximum.
 * @param periodic The number of bytes remaining in your upload quota for the current period.
 * @param quota Quota information.
 * @param space Space information.
 */
@JsonClass(generateAdapter = true)
data class UploadQuota(

    @Json(name = "lifetime")
    val lifetime: Lifetime? = null,

    @Json(name = "periodic")
    val periodic: Periodic? = null,

    @Json(name = "quota")
    val quota: Quota? = null,

    @Json(name = "space")
    val space: Space? = null

)
