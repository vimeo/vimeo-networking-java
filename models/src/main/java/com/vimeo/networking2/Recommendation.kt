@file:JvmName("RecommendationUtils")

package com.vimeo.networking2

import com.vimeo.networking2.enums.RecommendationType
import com.vimeo.networking2.enums.asEnum

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
    val type: String? = null,

    /**
     * The user that is being recommended.
     */
    val user: User? = null

) : Entity {

    override val identifier: String? = resourceKey

}

/**
 * @see Recommendation.type
 */
val Recommendation.recommendationType: RecommendationType
    get() = type.asEnum(RecommendationType.UNKNOWN)
