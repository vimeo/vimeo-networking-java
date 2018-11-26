package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal

/**
 * Purchase season interaction.
 */
@Internal
@JsonClass(generateAdapter = true)
data class PurchaseOnDemandInteraction(

    /**
     * Whether the On Demand video for purchase has DRM.
     */
    @Internal
    @Json(name = "buy")
    val buy: BuyInteraction? = null,

    /**
     * Subscribe to on demand video.
     */
    @Internal
    @Json(name = "subscribe")
    val subscriptionInteraction: SubscriptionInteraction? = null

)
