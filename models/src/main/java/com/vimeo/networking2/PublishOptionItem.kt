package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.Entity
import java.io.Serializable

/**
 * A page or category that can be sent when publishing to a social media platform.
 */
@JsonClass(generateAdapter = true)
data class PublishOptionItem(

    /**
     * The ID of the publish item.
     */
    @Json(name = "id")
    val id: String? = null,

    /**
     * The name or display name of the publish item, i.e.: "art", "family", "vacation" etc.
     */
    @Json(name = "name")
    val name: String? = null

) : Entity, Serializable {

    override val identifier: String? = id

    companion object {
        private const val serialVersionUID = -3563L
    }
}
