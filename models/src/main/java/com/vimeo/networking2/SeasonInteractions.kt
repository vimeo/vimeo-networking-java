package com.vimeo.networking2

/**
 * All actions that can be taken on a [Season].
 */
data class SeasonInteractions(

    /**
     * The interactions for an On Demand video.
     *
     * Requires [CapabilitiesType.CAPABILITY_MAKE_PURCHASES]
     */
    val purchase: PurchaseOnDemandInteraction? = null

)
