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
 * @param showing Whether the values of the upload_quota.space fields are for the lifetime quota or the periodic quota.
 * See [Space.showingType].
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
