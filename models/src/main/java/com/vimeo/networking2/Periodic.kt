package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable
import com.vimeo.networking2.common.StorageQuota

/**
 * Periodic upload quota information.
 */
@JsonClass(generateAdapter = true)
data class Periodic(

    @Json(name = "free")
    override val free: Long? = null,

    @Json(name = "max")
    override val max: Long? = null,

    @Json(name = "used")
    override val used: Long? = null

) : StorageQuota, Serializable {

    companion object {
        private const val serialVersionUID = -41L
    }
}
