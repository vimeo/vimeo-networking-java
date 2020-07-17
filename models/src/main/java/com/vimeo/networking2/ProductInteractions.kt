package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Purchase a product action information.
 */
@JsonClass(generateAdapter = true)
data class ProductInteractions(

    /**
     * Purchase product.
     */
    @Json(name = "purchase")
    val purchase: PurchaseInteraction? = null

)
