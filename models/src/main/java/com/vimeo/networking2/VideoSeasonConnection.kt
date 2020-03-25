package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

/**
 * Information about the season the video is associated with.
 */
@JsonClass(generateAdapter = true)
data class VideoSeasonConnection(

    /**
     * An array of HTTP methods permitted on this URI.
     */
    @Json(name = "options")
    val options: List<String>? = null,

    /**
     * The API URI that resolves to the connection data.
     */
    @Json(name = "uri")
    val uri: String? = null,

    /**
     * The name of the season.
     */
    @Json(name = "name")
    val name: String? = null
) : Serializable {
    companion object {
        private const val serialVersionUID = -90000093L
    }
}
