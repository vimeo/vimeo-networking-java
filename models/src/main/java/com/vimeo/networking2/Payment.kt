package com.vimeo.networking2

import com.vimeo.networking2.enums.PaymentType

data class Payment(

    /**
     * Whether this payment method is active.
     */
    val active: Boolean?,

    /**
     * Credit card information.
     */
    val cc: CC?,

    /**
     * The type of stored payment method
     */
    val type: PaymentType

)
