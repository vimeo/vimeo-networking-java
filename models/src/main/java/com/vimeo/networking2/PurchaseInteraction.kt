package com.vimeo.networking2

import com.vimeo.networking2.common.Interaction
import com.vimeo.networking2.enums.ApiOptionsType
import com.vimeo.networking2.enums.PurchaseStatusType

/**
 * Purchase a video action data.
 */
data class PurchaseInteraction(

    override val options: List<ApiOptionsType>? = null,

    override val uri: String? = null,

    val status: PurchaseStatusType = PurchaseStatusType.UNKNOWN

): Interaction
