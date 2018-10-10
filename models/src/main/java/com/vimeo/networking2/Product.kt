package com.vimeo.networking2

import com.vimeo.networking2.enums.BillingPeriodType

/**
 * Product data.
 */
data class Product(

    /**
     * Distinguish between monthly and yearly products.
     */
    val billingPeriod: BillingPeriodType? = null,

    /**
     * Product description.
     */
    val description: String? = null,

    /**
     * Metadata about the product.
     */
    val metadata: MetadataInteractions<ProductInteractions>? = null,

    /**
     * Product name
     */
    val name: String? = null,

    /**
     * Product ID.
     */
    val productId: String? = null,

    /**
     * The unique identifier you can use to access the product.
     */
    val uri: String? = null

)
