@file:JvmName("PurchaseInteractionUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.Interaction
import com.vimeo.networking2.enums.PurchaseStatusType
import com.vimeo.networking2.enums.asEnum

/**
 * Purchase a video action data.
 */
@JsonClass(generateAdapter = true)
data class PurchaseInteraction(

    @Json(name = "options")
    override val options: List<String>? = null,

    @Json(name = "uri")
    override val uri: String? = null,

    /**
     * Purchase status.
     * @see PurchaseInteraction.purchaseStatusType
     */
    @Json(name = "status")
    val purchaseStatus: String? = null

) : Interaction

/**
 * @see PurchaseInteraction.purchaseStatus
 * @see PurchaseStatusType
 */
val PurchaseInteraction.purchaseStatusType: PurchaseStatusType
    get() = purchaseStatus.asEnum(PurchaseStatusType.UNKNOWN)
