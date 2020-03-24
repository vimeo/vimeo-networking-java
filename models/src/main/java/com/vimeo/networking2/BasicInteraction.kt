package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal
import com.vimeo.networking2.common.Interaction
import java.io.Serializable

/**
 * Interaction with options and uri information.
 */
@Internal
@JsonClass(generateAdapter = true)
data class BasicInteraction(

        @Internal
        @Json(name = "options")
        override val options: List<String>? = null,

        @Internal
        @Json(name = "uri")
        override val uri: String? = null

) : Interaction, Serializable {
    companion object {
        private const val serialVersionUID = -2408407L
    }
}
