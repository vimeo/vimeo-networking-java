@file:JvmName("SpaceUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.StorageQuota
import com.vimeo.networking2.enums.UploadSpaceType
import com.vimeo.networking2.enums.asEnum

/**
 * Upload quota space data.
 *
 * @param showing The amount of time represented by the available space quota representation (e.g. lifetime or
 * periodic). See [Space.showingType].
 */
@JsonClass(generateAdapter = true)
data class Space(

    @Json(name = "free")
    override val free: Long? = null,

    @Json(name = "max")
    override val max: Long? = null,

    @Json(name = "showing")
    val showing: String? = null,

    @Json(name = "used")
    override val used: Long? = null

) : StorageQuota

/**
 * @see Space.showing
 * @see UploadSpaceType
 */
val Space.showingType: UploadSpaceType
    get() = showing.asEnum(UploadSpaceType.UNKNOWN)
