package com.vimeo.networking2
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

import java.util.*

/**
 * Requires [CapabilitiesType.CapabilitiesType.CAPABILITY_MAKE_PURCHASES].
 */
@JsonClass(generateAdapter = true)
data class CreditCard(

    /**
     * The expiration date in MM/YY format of the user's credit card.
     */
    @Json(name = "expiration_date")
    val expirationDate: Date? = null,

    /**
     * The last four digits of the user's credit card.
     */
    @Json(name = "last_four")
    val lastFour: Int? = null,

    /**
     * The postal code of the billing address associated with the user's credit card.
     */
    @Json(name = "postal_code")
    val postalCode: Int? = null,

    /**
     * The type of credit card.
     */
    @Json(name = "type")
    val type: String? = null

)
