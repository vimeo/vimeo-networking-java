package com.vimeo.networking2

import com.vimeo.networking2.common.Entity
import com.vimeo.networking2.enums.RecommendationType

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
    val type: RecommendationType? = null,

    /**
     * The user that is being recommended.
     */
    val user: User? = null

) : Entity {

    override val identifier: String? = resourceKey

}
