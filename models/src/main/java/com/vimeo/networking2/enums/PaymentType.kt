package com.vimeo.networking2.enums

/**
 * Different types of payments.
 */
enum class PaymentType(override val value: String?) : StringValue {

    /**
     * The stored payment method is a credit card.
     */
    CC("cc"),

    /**
     * Unknown payment type.
     */
    UNKNOWN(null)
}
