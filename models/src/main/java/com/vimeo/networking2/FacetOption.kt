package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

/**
 * Facet data.
 */
@JsonClass(generateAdapter = true)
data class FacetOption(

    /**
     * Option name.
     */
    @Json(name = "name")
    val name: String? = null,

    /**
     * Option total.
     */
    @Json(name = "total")
    val total: Int? = null,

    /**
     * Option text.
     */
    @Json(name = "text")
    val text: String? = null

): Serializable {

    companion object {
        private const val serialVersionUID = -6019838L
    }
}
