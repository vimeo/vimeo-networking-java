package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.Entity

/**
 * A page or category that can be sent when publishing to a social media platform.
 *
 * @param id The ID of the publish item.
 * @param name The name or display name of the publish item, i.e.: "art", "family", "vacation" etc.
 */
@JsonClass(generateAdapter = true)
data class PublishOptionItem(

    @Json(name = "id")
    val id: String? = null,

    @Json(name = "name")
    val name: String? = null

) : Entity {
    override val identifier: String? = id
}
