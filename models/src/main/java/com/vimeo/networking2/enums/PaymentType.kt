package com.vimeo.networking2.enums

import com.squareup.moshi.Json

/**
 * Different types of payments.
 */
enum class PaymentType {

    /**
     * The stored payment method is a credit card.
     */
    @Json(name = "cc")
    CC,

    /**
     * Unknown payment type.
     */
    UNKNOWN
}
