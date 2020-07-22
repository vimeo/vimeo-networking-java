@file:JvmName("SpaceUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.StorageQuota
import com.vimeo.networking2.enums.UploadSpaceType
import com.vimeo.networking2.enums.asEnum

/**
 * Upload quota space data.
 */
@JsonClass(generateAdapter = true)
data class Space(

    /**
     * The number of bytes remaining in your upload quota.
     */
    @Json(name = "free")
    override val free: Long? = null,

    /**
     * The maximum number of bytes allotted to your upload quota.
     */
    @Json(name = "max")
    override val max: Long? = null,

    /**
     * Whether the values of the upload_quota.space fields are for the lifetime quota or
     * the periodic quota.
     * @see Space.showingType
     */
    @Json(name = "showing")
    val showing: String? = null,

    /**
     * The number of bytes that you've already uploaded against your quota.
     */
    @Json(name = "used")
    override val used: Long? = null

) : StorageQuota

/**
 * @see Space.showing
 * @see UploadSpaceType
 */
val Space.showingType: UploadSpaceType
    get() = showing.asEnum(UploadSpaceType.UNKNOWN)
