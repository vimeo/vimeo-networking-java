package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

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

): Serializable {

    companion object {
        private const val serialVersionUID = -14787L
    }
}
