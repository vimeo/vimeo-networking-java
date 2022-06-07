package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.StorageQuota
import com.vimeo.networking2.enums.UploadQuotaPeriodType
import com.vimeo.networking2.enums.UploadQuotaUnitType
import com.vimeo.networking2.enums.asEnum
import java.util.Date

/**
 * Periodic upload quota information.
 *
 * @param period The renewal frequency of the quota. See [Periodic.periodType].
 * @param resetDate The time in ISO 8601 format when the authenticated user's upload quota resets.
 */
@JsonClass(generateAdapter = true)
data class Periodic(

    @Json(name = "free")
    override val free: Long? = null,

    @Json(name = "max")
    override val max: Long? = null,

    @Json(name = "period")
    val period: String? = null,

    @Json(name = "reset_date")
    val resetDate: Date? = null,

    @Json(name = "unit")
    override val unit: String? = null,

    @Json(name = "used")
    override val used: Long? = null

) : StorageQuota

/**
 * @see Periodic.period
 * @see UploadQuotaPeriodType
 */
val Periodic.periodType: UploadQuotaPeriodType
    get() = period.asEnum(UploadQuotaPeriodType.UNKNOWN)

/**
 * @see Periodic.unit
 * @see UploadQuotaUnitType
 */
val Periodic.unitType: UploadQuotaUnitType
    get() = unit.asEnum(UploadQuotaUnitType.UNKNOWN)
