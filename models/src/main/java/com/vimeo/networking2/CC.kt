package com.vimeo.networking2

import java.util.*

data class CC(

    /**
     * The expiration date in MM/YY format of the user's credit card.
     *
     * Based on CAPABILITY_MAKE_PURCHASES.
     */
    val expirationDate: Date? = null,

    /**
     * The last four digits of the user's credit card.
     *
     * Based on CAPABILITY_MAKE_PURCHASES.
     */
    val lastFour: Int? = null,

    /**
     * The postal code of the billing address associated with the user's credit card.
     *
     * Based on CAPABILITY_MAKE_PURCHASES.
     */
    val postalCode: Int? = null,

    /**
     * The type of credit card.
     *
     * Based on CAPABILITY_MAKE_PURCHASES.
     */
    val type: String? = null

)
