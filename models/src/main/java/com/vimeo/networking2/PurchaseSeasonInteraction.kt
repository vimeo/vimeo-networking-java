package com.vimeo.networking2

/**
 * Purchase season interaction.
 */
data class PurchaseSeasonInteraction(

    /**
     * Whether the On Demand video for purchase has DRM.
     */
    val buy: BuyInteraction? = null,

    /**
     * Subscribe to season.
     */
    val subscriptionInteraction: SubscriptionInteraction? = null

)
