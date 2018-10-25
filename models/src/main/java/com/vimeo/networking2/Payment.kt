package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.PaymentType

@JsonClass(generateAdapter = true)
data class Payment(

    /**
     * Whether this payment method is active.
     */
    @Json(name = "active")
    val active: Boolean? = null,

    /**
     * Credit card information.
     */
    @Json(name = "cc")
    val creditCard: CreditCard? = null,

    /**
     * The type of stored payment method
     */
    @Json(name = "type")
    val type: PaymentType? = null

)
