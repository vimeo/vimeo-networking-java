@file:JvmName("ProductUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.BillingPeriodType
import com.vimeo.networking2.enums.asEnum

/**
 * Product data.
 *
 * @param billingPeriod The monthly or yearly billing period of the product. See [Product.billingPeriodType].
 * @param description Product description.
 * @param metadata Metadata about the product.
 * @param name Product name.
 * @param productId Product ID.
 * @param uri The unique identifier you can use to access the product.
 */
@JsonClass(generateAdapter = true)
data class Product(

    @Json(name = "billing_period")
    val billingPeriod: String? = null,

    @Json(name = "description")
    val description: String? = null,

    @Json(name = "metadata")
    val metadata: MetadataInteractions<ProductInteractions>? = null,

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "product_id")
    val productId: String? = null,

    @Json(name = "uri")
    val uri: String? = null

)

/**
 * @see Product.billingPeriod
 * @see BillingPeriodType
 */
val Product.billingPeriodType: BillingPeriodType
    get() = billingPeriod.asEnum(BillingPeriodType.UNKNOWN)
