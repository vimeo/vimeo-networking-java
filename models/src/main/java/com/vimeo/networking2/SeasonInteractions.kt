package com.vimeo.networking2

import com.vimeo.networking2.annotations.Internal

/**
 * All actions that can be taken on a [Season].
 */
data class SeasonInteractions(

    /**
     * The interactions for an On Demand video.
     */
    @Internal
    val purchase: PurchaseOnDemandInteraction? = null

)
