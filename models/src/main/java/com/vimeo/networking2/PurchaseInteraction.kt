package com.vimeo.networking2

import com.vimeo.networking2.common.Interaction
import com.vimeo.networking2.enums.ApiOptionsType
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.PurchaseStatusType

/**
 * Purchase a video action data.
 */
@JsonClass(generateAdapter = true)
data class PurchaseInteraction(

    @Json(name = "options")
    override val options: List<ApiOptionsType>? = null,

    @Json(name = "uri")
    override val uri: String? = null,

    /**
     * Purchase status.
     */
    @Json(name = "status")
    val status: PurchaseStatusType = PurchaseStatusType.UNKNOWN

): Interaction
