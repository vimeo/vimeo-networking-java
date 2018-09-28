package com.vimeo.networking2

import com.vimeo.networking2.enums.PurchaseStatusType

data class PurchaseInteraction(

    /**
     * Purchase status.
     */
    val status: PurchaseStatusType?,

    /**
     * URI for creating a purchase.
     */
    val uri: String?

)
