@file:JvmName("RecommendationUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.Entity
import com.vimeo.networking2.enums.RecommendationType
import com.vimeo.networking2.enums.asEnum

/**
 * Recommendation DTO.
 *
 * @param channel The recommended channel.
 * @param description The reason for the recommendation.
 * @param resourceKey The recommendation's resource key string.
 * @param rawType Type of recommendation. See [Recommendation.type].
 * @param user The user that is being recommended.
 */
@JsonClass(generateAdapter = true)
data class Recommendation(

    @Json(name = "channel")
    val channel: Channel? = null,

    @Json(name = "description")
    val description: String? = null,

    @Json(name = "resource_key")
    val resourceKey: String? = null,

    @Json(name = "type")
    val rawType: String? = null,

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
