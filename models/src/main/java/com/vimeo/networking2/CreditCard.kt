package com.vimeo.networking2

import java.util.*

/**
 * Requires [CapabilitiesType.CapabilitiesType.CAPABILITY_MAKE_PURCHASES].
 */
data class CreditCard(

    /**
     * The expiration date in MM/YY format of the user's credit card.
     */
    val expirationDate: Date? = null,

    /**
     * The last four digits of the user's credit card.
     */
    val lastFour: Int? = null,

    /**
     * The postal code of the billing address associated with the user's credit card.
     */
    val postalCode: Int? = null,

    /**
     * The type of credit card.
     */
    val type: String? = null

)
