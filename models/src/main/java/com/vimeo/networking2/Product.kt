package com.vimeo.networking2

import com.vimeo.networking2.enums.BillingPeriodType

data class Product(

    /**
     * Distinguish between monthly and yearly products.
     */
    val billingPeriod: BillingPeriodType?,

    /**
     * Product description.
     */
    val description: String?,

    /**
     * Metadata about the product.
     */
    val metadata: ProductMetadata?,

    /**
     * Product name
     */
    val name: String?,

    /**
     * Product ID.
     */
    val productId: String?,

    /**
     * The unique identifier you can use to access the product.
     */
    val uri: String?

)
