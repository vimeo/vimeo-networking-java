package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

/**
 * All connections for a cinema.
 */
@JsonClass(generateAdapter = true)
data class CinemaConnections(

        /**
         * Information about the contents of this programmed cinema item.
         */
        @Json(name = "contents")
        val contents: Connection? = null

) : Serializable {

    companion object {
        private const val serialVersionUID = -5768L
    }
}
