package com.vimeo.networking2

import com.vimeo.networking2.enums.PurchaseStatusType

/**
 * Purchase a video action data.
 */
data class PurchaseInteraction(

    /**
     * Purchase status.
     */
    val status: PurchaseStatusType? = null,

    /**
     * URI for creating a purchase.
     */
    val uri: String? = null

)
