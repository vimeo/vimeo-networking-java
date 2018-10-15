package com.vimeo.networking2

/**
 * Purchase season interaction.
 */
data class PurchaseOnDemandInteraction(

    /**
     * Whether the On Demand video for purchase has DRM.
     */
    val buy: BuyInteraction? = null,

    /**
     * Subscribe to on demand video.
     */
    val subscriptionInteraction: SubscriptionInteraction? = null

)
