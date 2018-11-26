@file:JvmName("RecommendationUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.Entity
import com.vimeo.networking2.enums.RecommendationType
import com.vimeo.networking2.enums.asEnum

/**
 * Recommendation DTO.
 */
@JsonClass(generateAdapter = true)
data class Recommendation(

    /**
     * The recommended channel.
     */
    @Json(name = "channel")
    val channel: Channel? = null,

    /**
     * The reason for the recommendation.
     */
    @Json(name = "description")
    val description: String? = null,

    /**
     * The recommendation's resource key string.
     */
    @Json(name = "resource_key")
    val resourceKey: String? = null,

    /**
     * Type of recommendation.
     * @see Recommendation.type
     */
    @Json(name = "type")
    val rawType: String? = null,

    /**
     * The user that is being recommended.
     */
    @Json(name = "user")
    val user: User? = null

) : Entity {

    override val identifier: String? = resourceKey

}

/**
 * @see Recommendation.rawType
 * @see RecommendationType
 */
val Recommendation.type: RecommendationType
    get() = rawType.asEnum(RecommendationType.UNKNOWN)
