@file:JvmName("TranscodeUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.TranscodeStatusType
import com.vimeo.networking2.enums.asEnum
import java.io.Serializable

/**
 * The transcode information for a video upload.
 */
@JsonClass(generateAdapter = true)
data class Transcode(

        /**
         * Status code for clip availability.
         * @see Transcode.statusType
         */
        @Json(name = "status")
        val status: String? = null

) : Serializable {

    companion object {
        private const val serialVersionUID = -31891L
    }
}

/**
 * @see Transcode.status
 * @see TranscodeStatusType
 */
val Transcode.statusType: TranscodeStatusType
    get() = status.asEnum(TranscodeStatusType.UNKNOWN)
