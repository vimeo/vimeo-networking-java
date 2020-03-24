package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

/**
 * Quota data.
 */
@JsonClass(generateAdapter = true)
data class Quota(

        /**
         * Whether you can upload HD videos.
         */
        @Json(name = "hd")
        val hd: Boolean? = null,

        /**
         * Whether you can upload SD videos.
         */
        @Json(name = "sd")
        val sd: Boolean? = null

) : Serializable {

    companion object {
        private const val serialVersionUID = -15L
    }
}
