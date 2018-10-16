package com.vimeo.networking2

import com.vimeo.networking2.enums.RecommendationType
import com.vimeo.networking2.enums.RecommendationType.UNKNOWN

/**
 * Recommendation DTO.
 */
data class Recommendation(

    /**
     * The recommended channel.
     */
    val channel: Channel? = null,

    /**
     * The reason for the recommendation.
     */
    val description: String? = null,

    /**
     * The recommendation's resource key string.
     */
    val resourceKey: String? = null,

    /**
     * Type of recommendation.
     */
    val type: RecommendationType = UNKNOWN,

    /**
     * The user that is being recommended.
     */
    val user: User? = null

)
