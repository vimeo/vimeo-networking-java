package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal

/**
 * Purchase season interaction.
 *
 * @param buy Whether the On Demand video for purchase has DRM.
 * @param subscriptionInteraction Subscribe to on demand video.
 */
@Internal
@JsonClass(generateAdapter = true)
data class PurchaseOnDemandInteraction(

    @Internal
    @Json(name = "buy")
    val buy: BuyInteraction? = null,

    @Internal
    @Json(name = "subscribe")
    val subscriptionInteraction: SubscriptionInteraction? = null

)
