package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.PurchaseStatusType

/**
 * Purchase a video action data.
 */
@JsonClass(generateAdapter = true)
data class PurchaseInteraction(

    /**
     * Purchase status.
     */
    @Json(name = "status")
    val status: PurchaseStatusType = PurchaseStatusType.UNKNOWN,

    /**
     * URI for creating a purchase.
     */
    @Json(name = "uri")
    val uri: String? = null

)
