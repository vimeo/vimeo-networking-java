package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.FollowableInteractions
import java.io.Serializable

/**
 * All actions that can be taken on a category.
 */
@JsonClass(generateAdapter = true)
data class CategoryInteractions(

        @Json(name = "follow")
        override val follow: FollowInteraction? = null

) : FollowableInteractions, Serializable {
    companion object {
        private const val serialVersionUID = -4572387L
    }
}
