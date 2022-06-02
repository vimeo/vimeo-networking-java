package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.StorageQuota
import com.vimeo.networking2.enums.UploadQuotaUnitType
import com.vimeo.networking2.enums.asEnum

/**
 * Lifetime data.
 */
@JsonClass(generateAdapter = true)
data class Lifetime(

    @Json(name = "free")
    override val free: Long? = null,

    @Json(name = "max")
    override val max: Long? = null,

    @Json(name = "unit")
    override val unit: String? = null,

    @Json(name = "used")
    override val used: Long? = null
) : StorageQuota


/**
 * @see Lifetime.unit
 * @see UploadQuotaUnitType
 */
val Lifetime.unitType: UploadQuotaUnitType
    get() = unit.asEnum(UploadQuotaUnitType.UNKNOWN)
