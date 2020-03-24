package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal
import java.io.Serializable

/**
 * Play progress information.
 */
@Internal
@JsonClass(generateAdapter = true)
data class PlayProgress(

        /**
         * The play progress in seconds.
         */
        @Internal
        @Json(name = "seconds")
        val seconds: Int? = null

) : Serializable {

    companion object {
        private const val serialVersionUID = -26L
    }
}
