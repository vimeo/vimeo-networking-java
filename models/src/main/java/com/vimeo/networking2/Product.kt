package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.BillingPeriodType

/**
 * Product data.
 */
@JsonClass(generateAdapter = true)
data class Product(

    /**
     * Distinguish between monthly and yearly products.
     */
    @Json(name = "billing_period")
    val billingPeriod: BillingPeriodType? = null,

    /**
     * Product description.
     */
    @Json(name = "description")
    val description: String? = null,

    /**
     * Metadata about the product.
     */
    @Json(name = "metadata")
    val metadata: MetadataInteractions<ProductInteractions>? = null,

    /**
     * Product name
     */
    @Json(name = "name")
    val name: String? = null,

    /**
     * Product ID.
     */
    @Json(name = "product_id")
    val productId: String? = null,

    /**
     * The unique identifier you can use to access the product.
     */
    @Json(name = "uri")
    val uri: String? = null

)
