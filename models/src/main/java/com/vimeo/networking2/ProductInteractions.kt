package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Purchase a product action information.
 *
 * @param purchase The interaction for purchasing a product.
 */
@JsonClass(generateAdapter = true)
data class ProductInteractions(

    @Json(name = "purchase")
    val purchase: PurchaseInteraction? = null

)
